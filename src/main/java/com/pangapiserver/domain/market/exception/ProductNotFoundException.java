package com.pangapiserver.domain.market.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class ProductNotFoundException extends BasicException {
    public ProductNotFoundException() {
        super(MarketExceptionStatusCode.PRODUCT_NOT_FOUND);
    }
}
