package project.jinshang.mod_invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_invoice.InvoiceInfoMapper;
import project.jinshang.mod_invoice.bean.InvoiceInfo;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    InvoiceInfoMapper invoiceInfoMapper;

    public InvoiceInfo getInvoiceInfoById(Long invoiceInfoId){
        return invoiceInfoMapper.selectByPrimaryKey(invoiceInfoId);
    }

    public void addInvoiceInfo(InvoiceInfo invoiceInfo){
        invoiceInfoMapper.insertSelective(invoiceInfo);
    }

    public void editInvoiceInfo(InvoiceInfo invoiceInfo){
        invoiceInfoMapper.updateByPrimaryKeySelective(invoiceInfo);
    }


    public List<InvoiceInfo> getInvoiceInfoListByMemberId(Long memberId){
        return invoiceInfoMapper.invoiceInfoListByMemberId(memberId);
    }

    public List<InvoiceInfo> getInvoiceInfoListByMemberId2(Long memberId){
        return invoiceInfoMapper.invoiceInfoListByMemberId2(memberId);
    }

    public InvoiceInfo getDefaultInvoiceInfoByMemberId(Long memberId){
        return invoiceInfoMapper.defaultInvoiceInfoByMemberId(memberId);
    }

    public void deleteInvoiceInfoById(Long invoiceId){
        invoiceInfoMapper.deleteByPrimaryKey(invoiceId);
    }

    public void deleteInvoiceInfoByMemberid(Long memberId){
        invoiceInfoMapper.deleteInvoiceInfoByMemberid(memberId);
    }

    public void updateInvoiceInfoByMemberid(Long memberId){
        int result = invoiceInfoMapper.updateInvoiceInfoByMemberid(memberId);
    }

//    public int getSameInvoiceCount(Long memberid,String invoiceheadup,String bankofaccounts,String texno,String account){
//        InvoiceInfoExample invoiceInfoExample = new InvoiceInfoExample();
//        InvoiceInfoExample.Criteria criteria = invoiceInfoExample.createCriteria();
//
//        criteria.andMemberidEqualTo(memberid);
//        criteria.andInvoiceheadupEqualTo(invoiceheadup);
//        criteria.andBankofaccountsEqualTo(bankofaccounts);
//        criteria.andTexnoEqualTo(texno);
//        criteria.andAccountEqualTo(account);
//
//        return invoiceInfoMapper.countByExample(invoiceInfoExample);
//    }

}
