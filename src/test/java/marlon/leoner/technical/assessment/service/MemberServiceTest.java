package marlon.leoner.technical.assessment.service;

import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.domain.model.Member;
import marlon.leoner.technical.assessment.domain.param.CreateMemberParam;
import marlon.leoner.technical.assessment.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository repository;

    @Mock
    private IntegrationService integrationService;

    @InjectMocks
    private MemberService memberService;

    private Member member;

    @BeforeEach
    void setup() {
        member = new Member("12345678900", "Member 1");
        member.setAbleToVote(true);
    }

    @Test
    void shouldReturnAllMembers() {
        when(repository.findAll()).thenReturn(List.of(member));

        List<Member> members = memberService.getAllMembers();

        assertNotNull(members);
        assertFalse(members.isEmpty());
        assertEquals(1, members.size());
    }

    @Test
    void shouldReturnMemberById() {
        when(repository.findById(anyString())).thenReturn(Optional.of(member));

        Optional<Member> found = memberService.getMemberById(anyString());

        assertTrue(found.isPresent());
        assertEquals("12345678900", found.get().getCpf());
    }

    @Test
    void shouldThrowExceptionWhenMemberNotFoundById() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            memberService.getMemberByIdOrException(anyString());
        });
    }

    @Test
    void shouldCreateMemberAndSetAbleToVote() {
        CreateMemberParam param = new CreateMemberParam("12345678900", "Member 1");

        when(integrationService.isCPFValid(param.getCpf())).thenReturn(true);
        when(repository.save(any(Member.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Member created = memberService.createMember(param);

        assertNotNull(created);
        assertEquals(param.getCpf(), created.getCpf());
        assertTrue(created.isAbleToVote());

        verify(integrationService).isCPFValid(param.getCpf());
        verify(repository).save(any(Member.class));
    }
}
