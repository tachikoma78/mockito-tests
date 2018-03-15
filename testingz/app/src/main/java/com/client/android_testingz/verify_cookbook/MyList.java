package com.client.android_testingz.verify_cookbook;

import java.util.ArrayList;

/**
 * http://www.baeldung.com/mockito-verify
 */

public class MyList extends ArrayList<String>{

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String get(final int index) {
        return null;
    }
}
