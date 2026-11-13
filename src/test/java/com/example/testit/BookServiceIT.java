package com.example.testit;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.testit.model.Book;
import com.example.testit.model.Member;
import com.example.testit.model.Status;
import com.example.testit.repository.BookRepository;
import com.example.testit.repository.MemberRepository;
import com.example.testit.service.BookService;

@SpringBootTest 
public class BookServiceIT {
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    MemberRepository memberRepository;

    @Autowired 
    BookService bookService;

    @Test 
    public void test1(){
        //given
        Member member1 = new Member();
        member1 = memberRepository.save(member1);
        
        Book book1 = new Book();
        book1.setStatus(Status.DISPONIBLE);
        book1 = bookRepository.save(book1);
        
        //when
        Book book2 = bookService.borrowBook(book1.getId(), member1.getId());
        Book book3 = bookService.returnBook(book2.getId(), member1.getId());
        
        //then
        Optional<Book> b1 = bookRepository.findById(book2.getId());
        Optional<Book> b2 = bookRepository.findById(book3.getId());
        Assertions.assertThat(b1.get().getStatus()).isEqualTo(Status.EMPRUNTE);
        Assertions.assertThat(b2.get().getStatus()).isEqualTo(Status.RENDU); // Ou DISPONIBLE ??
    
    }


}
