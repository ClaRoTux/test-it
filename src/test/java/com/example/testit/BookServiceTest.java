package com.example.testit;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.testit.adapter.mail.MailService;
import com.example.testit.model.Book;
import com.example.testit.model.Member;
import com.example.testit.model.Status;
import com.example.testit.repository.BookRepository;
import com.example.testit.repository.MemberRepository;
import com.example.testit.service.BookService;

public class BookServiceTest {

    BookService bookService;
    BookRepository bookRepository;
    MemberRepository memberRepository;
    MailService mailService;

    @BeforeEach 
    void setup(){
        mailService = Mockito.mock(MailService.class);
        bookRepository =  Mockito.mock(BookRepository.class);
        memberRepository =  Mockito.mock(MemberRepository.class);
        bookService =  new BookService(bookRepository, memberRepository, mailService);
    }

    @Test
    public void test1(){
        //given
        Book book1 = new Book();
        book1.setId((long) 1);
        book1.setStatus(Status.DISPONIBLE);
        bookRepository.save(book1);
        List<Book> books = new ArrayList<>();
        Member member1 = new Member("member1");
        member1.setId((long) 2);
        memberRepository.save(member1);

        //when
        Mockito.when(bookService.findById(book1.getId())).thenReturn(Optional.of(book1));
        Mockito.when(memberRepository.findById(member1.getId())).thenReturn(Optional.of(member1));
        Mockito.when(bookRepository.findByMemberAndStatus(member1.getId(), Status.RENDU)).thenReturn(books);
        
        bookService.borrowBook(book1.getId(), member1.getId());

        //then
        Assertions.assertThat(book1.getStatus()).isEqualTo(Status.EMPRUNTE);
        Assertions.assertThat(book1.getBorrower()).isEqualTo(member1);
        Mockito.verify(bookRepository).findByMemberAndStatus(member1.getId(), Status.EMPRUNTE);
    }
}
