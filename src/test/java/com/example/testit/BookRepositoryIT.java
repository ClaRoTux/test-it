package com.example.testit;


import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.testit.model.Book;
import com.example.testit.model.Member;
import com.example.testit.model.Status;
import com.example.testit.repository.BookRepository;
import com.example.testit.repository.MemberRepository;

@SpringBootTest 
public class BookRepositoryIT {
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    MemberRepository memberRepository;

    @Test 
    public void testFindByMemberAndStatus(){
        //given
        Member member1 = new Member();
        member1 = memberRepository.save(member1);
        
        Book book1 = new Book();
        book1.setStatus(Status.EMPRUNTE);
        book1.setBorrower(member1);
        book1 = bookRepository.save(book1);

        //when
        List<Book> books = bookRepository.findByMemberAndStatus(member1.getId(), Status.EMPRUNTE);

        //then
        Assertions.assertThat(books.size()).isEqualTo(1);

    }
}
