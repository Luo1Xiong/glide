package com.bumptech.glide.load.resource;

import static com.bumptech.glide.tests.Util.mockResource;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import android.app.Application;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.tests.KeyTester;
import com.bumptech.glide.tests.Util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UnitTransformationTest {
    @Rule public final KeyTester keyTester = new KeyTester();

    private Application app;

    @Before
    public void setUp() {
        app = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void testReturnsGivenResource() {
        Resource<Object> resource = mockResource();
        UnitTransformation<Object> transformation = UnitTransformation.get();
        assertEquals(resource, transformation.transform(app, resource, 10, 10));
    }

    @Test
    public void testEqualsHashCodeDigest() throws NoSuchAlgorithmException {
        @SuppressWarnings("unchecked")
        Transformation<Object> other = mock(Transformation.class);
        doAnswer(new Util.WriteDigest("other"))
                .when(other)
                .updateDiskCacheKey(any(MessageDigest.class));

        keyTester
                .addEquivalenceGroup(UnitTransformation.get(), UnitTransformation.get())
                .addEquivalenceGroup(other)
                .addEmptyDigestRegressionTest(UnitTransformation.get())
                .test();
    }
}
