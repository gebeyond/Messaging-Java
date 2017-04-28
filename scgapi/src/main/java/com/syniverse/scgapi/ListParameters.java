/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi;

/**
 * Customize the result-set from queries
 *
 * @author jaase
 */
public class ListParameters {

    /**
     * Start the listing at the # item in the result-set.
     * <p>
     * This allows you to for example split a listing/processing
     * job of a huge result-set to several threads, where each
     * process a chunk of the full result-set.
     */
    Long start_offset = null;

    /**
     * ow many objects to return in response of one query.
     * <br>
     * Note: The server has a max value that cannot be overridden.
     */
    Integer page_size = null;

    /**
     * Sort criteria
     */
    String sort = null;
}
