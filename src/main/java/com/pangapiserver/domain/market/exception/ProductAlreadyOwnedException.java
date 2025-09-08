package com.pangapiserver.domain.market.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class ProductAlreadyOwnedException extends BasicException {
    public ProductAlreadyOwnedException() {
        super(MarketExceptionStatusCode.PRODUCT_ALREADY_OWNED);
    }
}
