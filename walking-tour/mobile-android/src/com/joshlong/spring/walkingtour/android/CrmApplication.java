package com.joshlong.spring.walkingtour.android;


import android.content.Context;
import org.springsource.examples.sawt.web.android.R;
import com.joshlong.spring.walkingtour.android.model.Customer;
import com.joshlong.spring.walkingtour.android.service.CustomerService;
import com.joshlong.spring.walkingtour.android.service.CustomerServiceClient;

import java.util.concurrent.atomic.AtomicReference;

public class CrmApplication {

    public CustomerService getCustomerService() {
        return customerService;
    }

    private Customer customer;

    // the customer being edited

    public Customer getCustomer() {
        return customer;
    }

    public synchronized void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private CustomerService customerService;

    public CrmApplication(String url) {
        this.customerService = new CustomerServiceClient(url);
    }

    public static CrmApplication crmApplicationInstance(Context context) {
        instance.compareAndSet(null, new CrmApplication(context.getString(R.string.base_uri)));
        return instance.get();
    }

    private static final AtomicReference<CrmApplication> instance = new AtomicReference<CrmApplication>();
}
