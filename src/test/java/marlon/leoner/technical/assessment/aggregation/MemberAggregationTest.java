package marlon.leoner.technical.assessment.aggregation;

import marlon.leoner.technical.assessment.domain.dto.MemberDTO;
import marlon.leoner.technical.assessment.domain.exception.ObjectAlreadyExistsException;
import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.domain.model.Member;
import marlon.leoner.technical.assessment.domain.param.CreateMemberParam;
import marlon.leoner.technical.assessment.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberAggregationTest {

    @Mock
    private MemberService service;

    @InjectMocks
    private MemberAggregation aggregation;

    private Member member;

    private MemberDTO memberDTO;

    @BeforeEach
    void setup() {
        member = new Member("12345678900", "Member 1");

        memberDTO = new MemberDTO();
        memberDTO.setName("Member 1");
        memberDTO.setCpf("12345678900");
    }

    @Test
    void shouldReturnAllMembersAsDTOs() {
        when(service.getAllMembers()).thenReturn(List.of(member));

        List<MemberDTO> result = aggregation.getAllMembers();

        assertEquals(1, result.size());
        verify(service).getAllMembers();
    }

    @Test
    void shouldReturnMemberDTOById() throws ObjectNotFoundException {
        when(service.getMemberByIdOrException(anyString())).thenReturn(member);

        MemberDTO result = aggregation.getMember(anyString());

        assertNotNull(result);
        assertEquals("12345678900", result.getCpf());
        verify(service).getMemberByIdOrException(anyString());
    }

    @Test
    void shouldCreateMemberSuccessfully() throws ObjectAlreadyExistsException {
        CreateMemberParam param = new CreateMemberParam();
        param.setCpf("12345678900");
        param.setName("Member 1");

        when(service.getMemberByCpf(anyString())).thenReturn(Optional.empty());
        when(service.createMember(param)).thenReturn(member);

        MemberDTO result = aggregation.createMember(param);

        assertNotNull(result);
        assertEquals("12345678900", result.getCpf());
        verify(service).getMemberByCpf(anyString());
        verify(service).createMember(param);
    }

    @Test
    void shouldThrowWhenMemberWithCpfAlreadyExists() {
        CreateMemberParam param = new CreateMemberParam();
        param.setCpf("12345678900");

        when(service.getMemberByCpf(anyString())).thenReturn(Optional.of(member));

        assertThrows(ObjectAlreadyExistsException.class, () -> {
            aggregation.createMember(param);
        });

        verify(service).getMemberByCpf(anyString());
        verify(service, never()).createMember(any());
    }
}
