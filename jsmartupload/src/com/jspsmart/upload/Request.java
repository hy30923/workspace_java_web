// 
// Decompiled by Procyon v0.5.36
// 

package com.jspsmart.upload;

import java.util.Enumeration;
import java.util.Hashtable;

public class Request
{
    private Hashtable m_parameters;
    private int m_counter;
    
    Request() {
        this.m_parameters = new Hashtable();
        this.m_counter = 0;
    }
    
    protected void putParameter(final String key, final String s) {
        if (key == null) {
            throw new IllegalArgumentException("The name of an element cannot be null.");
        }
        if (this.m_parameters.containsKey(key)) {
            final Hashtable<Integer, String> hashtable = (Hashtable<Integer, String>) this.m_parameters.get(key);
            hashtable.put(new Integer(hashtable.size()), s);
        }
        else {
            final Hashtable<Integer, String> value = new Hashtable<Integer, String>();
            value.put(new Integer(0), s);
            this.m_parameters.put(key, value);
            ++this.m_counter;
        }
    }
    
    public String getParameter(final String key) {
        if (key == null) {
            throw new IllegalArgumentException("Form's name is invalid or does not exist (1305).");
        }
        final Hashtable<Object, String> hashtable = (Hashtable<Object, String>) this.m_parameters.get(key);
        if (hashtable == null) {
            return null;
        }
        return hashtable.get(new Integer(0));
    }
    
    public Enumeration getParameterNames() {
        return this.m_parameters.keys();
    }
    
    public String[] getParameterValues(final String key) {
        if (key == null) {
            throw new IllegalArgumentException("Form's name is invalid or does not exist (1305).");
        }
        final Hashtable<Object, String> hashtable = (Hashtable<Object, String>) this.m_parameters.get(key);
        if (hashtable == null) {
            return null;
        }
        final String[] array = new String[hashtable.size()];
        for (int i = 0; i < hashtable.size(); ++i) {
            array[i] = hashtable.get(new Integer(i));
        }
        return array;
    }
}
