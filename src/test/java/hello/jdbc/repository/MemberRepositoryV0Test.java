package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        Member member = new Member("memberV5", 10000);
        repository.save(member);

        Member findMember = repository.findById(member.getMemeberId());
        log.info("findMember={}", findMember);
        assertThat(findMember).isEqualTo(member);

        repository.update(member.getMemeberId(),  20000);
        Member updateMember = repository.findById(member.getMemeberId());
        assertThat(updateMember.getMoney()).isEqualTo(20000);

        repository.delete(member.getMemeberId());
        assertThatThrownBy(() -> repository.findById(member.getMemeberId())).isInstanceOf(NoSuchElementException.class);
    }
}