/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.eclipse.org/org/documents/epl-v10.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.ide.eclipse.adt.internal.resources;


import com.android.ide.common.api.IAttributeInfo.Format;
import com.android.ide.eclipse.tests.AdtTestData;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.TestCase;

public class AttrsXmlParserManifestTest extends TestCase {

    private AttrsXmlParser mParser;
    private String mFilePath;

    private static final String MOCK_DATA_PATH =
        "com/android/ide/eclipse/testdata/mock_manifest_attrs.xml"; //$NON-NLS-1$

    @Override
    public void setUp() throws Exception {
        mFilePath = AdtTestData.getInstance().getTestFilePath(MOCK_DATA_PATH); //$NON-NLS-1$
        mParser = new AttrsXmlParser(mFilePath);
    }

    @Override
    public void tearDown() throws Exception {
    }

    public void testGetOsAttrsXmlPath() throws Exception {
        assertEquals(mFilePath, mParser.getOsAttrsXmlPath());
    }

    public final void testPreload() throws Exception {
        assertSame(mParser, mParser.preload());

        Map<String, DeclareStyleableInfo> styleableList = mParser.getDeclareStyleableList();
        // For testing purposes, we want the strings sorted
        if (!(styleableList instanceof TreeMap<?, ?>)) {
            styleableList = new TreeMap<String, DeclareStyleableInfo>(styleableList);
        }

        assertEquals(
                "[AndroidManifest, " +
                "AndroidManifestActivityAlias, " +
                "AndroidManifestApplication, " +
                "AndroidManifestNewElement, " +
                "AndroidManifestPermission" +
                // TODO This is for the next CL:
                //"AndroidManifestImplicitParentElement" +
                //"AndroidManifestNewElement" +
                "]",
                Arrays.toString(styleableList.keySet().toArray()));

        // TODO test a bit more the styleable info vlaues.
    }
}
