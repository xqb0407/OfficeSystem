package xyz.oa.Test;

import org.junit.Test;
import xyz.oa.entity.LeaveFrom;
import xyz.oa.service.LeaveFromService;
import xyz.oa.service.impl.LeaveFromServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaveFromServiceImplTest {
    private LeaveFromService leaveFromService = new LeaveFromServiceImpl();
    @Test
    public void createLeaveFrom() throws ParseException {
        LeaveFrom from = new LeaveFrom();
        from.setEmployeeId(1L);
        from.setReason("总经理探亲！！！");
        from.setFormType(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        from.setStartTime(sdf.parse("2021021008"));
        from.setEndTime(sdf.parse("2021021518"));
        from.setCreateTime(new Date());
        LeaveFrom leaveFrom = leaveFromService.createLeaveFrom(from);
        System.out.println(leaveFrom.getFormId());
    }

    @Test
    public void TestAudio(){
        leaveFromService.ApproveFrom(1L,33L,"approved","同意");
    }
    @Test
    public void TestAudio1(){
        leaveFromService.ApproveFrom(2L,32L,"refused","不行");
    }


}