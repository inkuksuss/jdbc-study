package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class MemberRepositoryV2Test {

    MemberRepositoryV2 repository;

    @BeforeEach
    void beforeEach() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        repository = new MemberRepositoryV2(dataSource);
    }

    @Test
    void crud() throws SQLException {
        Member member = new Member("memberV5", 10000);
        repository.save(member);

        Member findMember = repository.findById(member.getMemeberId());
        assertThat(findMember).isEqualTo(member);

        repository.update(member.getMemeberId(),  20000);
        Member updateMember = repository.findById(member.getMemeberId());
        assertThat(updateMember.getMoney()).isEqualTo(20000);

        repository.delete(member.getMemeberId());
        assertThatThrownBy(() -> repository.findById(member.getMemeberId())).isInstanceOf(NoSuchElementException.class);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}