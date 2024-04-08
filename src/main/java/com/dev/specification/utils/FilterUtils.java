package com.dev.specification.utils;

import com.dev.specification.exception.FilterIllegalArgumentException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class FilterUtils {
    private static final String SORT_ORDER = "sortOrder";
    private static final String PAGE = "page";
    private static final String SIZE = "size";
    private static final String SORT_BY = "sortBy";
    private static final String DEFAULT_SORT_PARAMETER = "producer";
    private static final String DEFAULT_PAGE_NUMBER = "0";
    private static final String DEFAULT_PAGE_SIZE = "5";
    private static final String ERROR_MESSAGE_PAGE_SIZE = "Page or size can not be null.";
    private static final String DEFAULT_SORT_ORDER = "asc";
    private static final String ERROR_MESSAGE_NUMBER_FORMAT = "Page or size should be a number.";

    public static Pageable getPageWithFilter(Map<String, String> filters) {
        try {
            return getPage(filters.get(SORT_BY) == null ? DEFAULT_SORT_PARAMETER : filters.remove(SORT_BY),
                    filters.get(SORT_ORDER) == null ? DEFAULT_SORT_ORDER : filters.remove(SORT_ORDER),
                    Integer.valueOf(filters.get(PAGE) == null ? DEFAULT_PAGE_NUMBER : filters.remove(PAGE)),
                    Integer.valueOf(filters.get(SIZE) == null ? DEFAULT_PAGE_SIZE : filters.remove(SIZE)));
        } catch (NumberFormatException e) {
            throw new FilterIllegalArgumentException(ERROR_MESSAGE_NUMBER_FORMAT);
        }
    }

    public static Pageable getPage(String sortBy, String sortOrder, Integer page, Integer size) {
        try {
            Sort.Direction orderingDirection = Sort.Direction.fromString(sortOrder);
            Sort sortByRequest = Sort.by(orderingDirection, sortBy);
            return PageRequest.of(page, size, sortByRequest);
        } catch (IllegalArgumentException e) {
            throw new FilterIllegalArgumentException(e.getMessage());
        } catch (NullPointerException e) {
            throw new FilterIllegalArgumentException(ERROR_MESSAGE_PAGE_SIZE);
        }
    }
}
