package project.jinshang.mod_bankaccount.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_bankaccount.BankAccountMapper;
import project.jinshang.mod_bankaccount.bean.BankAccount;
import project.jinshang.mod_bankaccount.bean.BankAccountExample;


import java.util.List;

@Service
/**
 * 银行帐号管理业务
 */
public class BankAccountService {

    @Autowired
    private BankAccountMapper bankAccountMapper;

    public void addBankAccount(BankAccount bankAccount) {
        bankAccountMapper.insert(bankAccount);
    }

    public void deleteBankAccountById(long id) {
        bankAccountMapper.deleteByPrimaryKey(id);
    }

    public void updateByPrimaryKeySelective(BankAccount bankAccount) {
        bankAccountMapper.updateByPrimaryKeySelective(bankAccount);
    }

    public List<BankAccount> listAllBankAccount() {
        BankAccountExample BankAccountExample = new BankAccountExample();
        return bankAccountMapper.selectByExample(BankAccountExample);
    }

    public BankAccount getById(long id) {
        return bankAccountMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取默认的银行卡信息
     *
     * @return
     */
    public BankAccount getDefaultBankAccount() {
        BankAccountExample BankAccountExample = new BankAccountExample();
        BankAccountExample.createCriteria().andIsdefaultEqualTo((short) 1);
        List<BankAccount> list = bankAccountMapper.selectByExample(BankAccountExample);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;
    }


    public int countByExample(BankAccountExample example) {
        return bankAccountMapper.countByExample(example);
    }

    /**
     * @param memberId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo getBankAccount(long memberId, short batype, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        BankAccountExample example = new BankAccountExample();
        BankAccountExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(memberId);
        if (batype > 0) {
            criteria.andBatypeEqualTo(batype);
        }
        example.setOrderByClause("id asc");
        List<BankAccount> list = bankAccountMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    public void updateIsdefault(short isdefault, short batype, long memberid) {
        bankAccountMapper.updateIsdefault(isdefault, batype, memberid);
    }

    public BankAccount selectDefaultBankCard(long memberId, short batype) {
        BankAccountExample example = new BankAccountExample();
        BankAccountExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(memberId);
        criteria.andBatypeEqualTo(batype);
        criteria.andIsdefaultEqualTo((short) 1);
        List<BankAccount> bankAccounts = bankAccountMapper.selectByExample(example);
        if (bankAccounts.size() != 0) {
            return bankAccounts.get(0);
        }
        return null;
    }

}
