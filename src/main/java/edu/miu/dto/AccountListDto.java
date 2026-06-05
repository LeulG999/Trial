package edu.miu.dto;

import java.math.BigDecimal;
import java.util.List;

public class AccountListDto {
    private List<AccountDto> accounts;
    private BigDecimal liquidityPosition;

    public AccountListDto() {
    }

    public AccountListDto(List<AccountDto> accounts, BigDecimal liquidityPosition) {
        this.accounts = accounts;
        this.liquidityPosition = liquidityPosition;
    }

    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
    }

    public BigDecimal getLiquidityPosition() {
        return liquidityPosition;
    }

    public void setLiquidityPosition(BigDecimal liquidityPosition) {
        this.liquidityPosition = liquidityPosition;
    }
}
