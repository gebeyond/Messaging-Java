/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import io.gsonfire.PostProcessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *
 * @author jaase
 */
public class BaseData {

    // Pointer to the BaseData.Resource that created this object.

    protected transient Object resource_ = null;

    static class ListReturnMapper<T> {
        @Expose List<T> list = null;
        @Expose Integer limit = null;
        @Expose Integer total = null;
    }

    interface Pager<T> {
        ListReturnMapper<T> fetch(long offset) throws ScgException;
    }

    static public class ItemItetaror<T> implements Iterable<T> {

        ListReturnMapper<T> currentPage = null;
        Pager<T> pager = null;
        private long offsetIndex = 0;
        int listIndex = 0;

        ItemItetaror(Pager<T> pager, Long startOffset) {
            this.pager = pager;
            if (startOffset != null) {
                offsetIndex = startOffset;
            }
        }

        // Convenience method for small result-sets
        public List<T> getAllAsList() {
            ArrayList<T> list = new ArrayList();
            for(T item : this) {
                list.add(item);
            }
            return list;
        }

        private boolean exhausted() {
            return (currentPage == null)
                    || (currentPage.list == null)
                    || (currentPage.list.size() <= listIndex);
        }

        protected boolean prepareNext() throws ScgException {
            if (exhausted()) {
                long offset = offsetIndex + listIndex;

                if ((currentPage != null) && (currentPage.total <= offset))
                    return false; // We are at the end.

                currentPage = pager.fetch(offset);
                offsetIndex = offset;
                listIndex = 0;
            }

            return !exhausted();
        }

        @Override
        public Iterator<T> iterator()  {
            Iterator<T> it = new Iterator<T>() {

                @Override
                public boolean hasNext() {
                    try {
                        return prepareNext();
                    } catch (ScgException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                @Override
                public T next() {
                    try {
                        if (!prepareNext()) {
                            throw new NoSuchElementException();
                        }
                    } catch (ScgException ex) {
                        throw new RuntimeException(ex);
                    }
                    int i = listIndex;
                    T item = currentPage.list.get(i);
                    listIndex++;
                    return item;
                }

            };
            return it;
        }

    }

    public interface ListExecutor<T> {
        ListReturnMapper<T> execute(Map<String, String> options) throws ScgException ;
    }


    static public <T> ItemItetaror<T> genericList(Map<String, String> options,
            ListParameters parameters,
            ListExecutor<T> executor) throws ScgException {

        if (options == null) {
            options = new HashMap<>();
        }

        Long startOffset = null;
        if (parameters != null) {
            startOffset = parameters.start_offset;

            if (parameters.page_size != null) {
                options.put("limit", Integer.toString(parameters.page_size));
            }

            if (parameters.sort != null) {
                options.put("sort", parameters.sort);
            }
        }

        return makeIterator(options, startOffset, executor);
    }

    static public <T> ItemItetaror<T> makeIterator(
            Map<String, String> options,
            Long startOffset,
            ListExecutor<T> executor) throws ScgException {

        return new ItemItetaror(offset -> {
            Map<String, String> myOptions = options;
            myOptions.put("offset",  Long.toString(offset));
            return executor.execute(myOptions);
        }, startOffset);
    }

    static class Deserialize<T> implements PostProcessor<T> {

        Class<T> clazz;
        Object attach;

        Deserialize(Object attach, Class<T> clazz) {
            this.clazz = clazz;
            this.attach = attach;
        }

        @Override
        public void postDeserialize(T result, JsonElement src, Gson gson) {
            ((BaseData)result).resource_ = attach;
        }

        @Override
        public void postSerialize(JsonElement result, T src, Gson gson) {
            // Do nothing
            int i = 0;
        }
    }

    public Object getResource() throws Error.NoResourceAttached {
        if (resource_ == null) {
            throw new Error.NoResourceAttached();
        }
        return resource_;
    }

}
