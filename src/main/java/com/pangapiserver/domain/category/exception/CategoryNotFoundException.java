package com.pangapiserver.domain.category.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class CategoryNotFoundException extends BasicException {
    public CategoryNotFoundException() {
        super(CategoryExceptionStatusCode.CATEGORY_NOT_FOUND);
    }
}
