package com.minibank.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    
    @RequestMapping(value="/main")
    public String mainPage() {
        return "index";
    }
    
    @RequestMapping(value="/customer/view/retrieveCustomer")
    public String retrieveCustomerPage() {
        return "customer/retrieveCustomer";
    }
    
    @RequestMapping(value="/customer/view/retrieveCustomerList")
    public String retrieveCustomerList() {
        return "customer/retrieveCustomerList";
    }
    
    @RequestMapping(value="/customer/view/retrieveCustomerCQRS")
    public String retrieveCustomerCQRSPage() {
        return "customer/retrieveCustomerCQRS";
    }
    
    @RequestMapping(value="/customer/view/createCustomer")
    public String createCustomerPage() {
        return "customer/createCustomer";
    }
    
    @RequestMapping(value="/customer/view/updateCustomer")
    public String updateCustomerPage() {
        return "customer/updateCustomer";
    }
    
    @RequestMapping(value="/customer/view/deleteCustomer")
    public String deleteCustomerPage() {
        return "customer/deleteCustomer";
    }
    
    @RequestMapping(value="/customer/view/retrieveDormantCustomer")
    public String retrieveDormantCustomerPage() {
        return "customer/retrieveDormantCustomer";
    }
    
    @RequestMapping(value="/cust/view/registerCust")
    public String registerCustPage() {
        return "cust/registerCust";
    }
    
    @RequestMapping(value="/cust/view/updateCust")
    public String updateCustPage() {
        return "cust/updateCust";
    }
    
    @RequestMapping(value="/cust/view/deleteCust")
    public String deleteCustPage() {
        return "cust/deleteCust";
    }
    
    @RequestMapping(value="/cust/view/retrieveCust")
    public String retrieveCustPage() {
        return "cust/retrieveCust";
    }
    
    @RequestMapping(value="/cust/view/retrieveCustList")
    public String retrieveCustListPage() {
        return "cust/retrieveCustList";
    }
    
    @RequestMapping(value="/cust/view/retrieveCustDetail")
    public String retrieveCustDetailPage() {
        return "cust/retrieveCustDetail";
    }
    
    @RequestMapping(value="/transfer/view/transfer")
    public String transferPage() {
        return "transfer/transfer";
    }
    
    @RequestMapping(value="/transfer/view/btobTransfer")
    public String btobTransferPage() {
        return "transfer/btobTransfer";
    }
    
    @RequestMapping(value="/transfer/view/retrieveTransferHistory")
    public String transferHistoryPage() {
        return "transfer/transferHistory";
    }
    
    @RequestMapping(value="/acct/view/registerAcct")
    public String registerAcctPage() {
        return "acct/registerAcct";
    }
    
    @RequestMapping(value="/acct/view/retrieveAcct")
    public String retrieveAcctPage() {
        return "acct/retrieveAcct";
    }
    
    @RequestMapping(value="/acct/view/updateAcct")
    public String updateAcctPage() {
        return "acct/updateAcct";
    }
    
    @RequestMapping(value="/acct/view/deleteAcct")
    public String deleteAcctPage() {
        return "acct/deleteAcct";
    }
    
    @RequestMapping(value="/acct/view/retrieveAcctList")
    public String retrieveAcctListPage() {
        return "acct/retrieveAcctList";
    }
    
    @RequestMapping(value="/account/view/createAccount")
    public String createAccountPage() {
        return "account/createAccount";
    }
    
    @RequestMapping(value="/account/view/deposit")
    public String depositPage() {
        return "account/deposit";
    }
    
    @RequestMapping(value="/account/view/withdraw")
    public String withdrawPage() {
        return "account/withdraw";
    }
    
    @RequestMapping(value="/account/view/retrieveTransactionHistory")
    public String retrieveTransactionHistoryPage() {
        return "account/retrieveTransactionHistory";
    }
}
