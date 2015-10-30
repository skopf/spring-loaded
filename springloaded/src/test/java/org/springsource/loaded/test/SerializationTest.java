/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springsource.loaded.test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;

import org.springsource.loaded.test.infra.TestClassLoader;


/**
 * Checking classloader issues when deserializing a composite object that contains objects that are not visible to the
 * root class loader. See: https://github.com/spring-projects/spring-loaded/issues/107
 *
 * @author Stefan Kopf, Alfresco
 * @since 1.2.5
 */
public class SerializationTest {

	public static void run() {
		try {
			SerializationTest st = new SerializationTest();
			st.testSerializeDeserializeArrayList();
			System.out.println("SUCCESS!!");
		}
		catch (Throwable t) {
			t.printStackTrace(System.out);
			System.out.println("EXCEPTION!!");
		}
	}

	private ClassLoader childLoader;

	public SerializationTest() throws Exception {
		URL[] childLoaderUrls = new URL[1];
		childLoaderUrls[0] = new File(TestUtils.getPathToClasses("../testdata")).toURI().toURL();
		childLoader = new TestClassLoader(childLoaderUrls, this.getClass().getClassLoader());
	}

	private void testSerializeDeserializeArrayList() throws Exception {
		Class<?> serializeDeserializeExecutorClass = Class.forName("serialization.SerializeDeserializeExecutor", false,
				childLoader);
		Method testMethod = serializeDeserializeExecutorClass.getMethod("serializeDeserializeArrayList",
				new Class<?>[0]);
		testMethod.invoke(null);
	}

}
