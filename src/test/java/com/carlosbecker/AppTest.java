package com.carlosbecker;

import static org.mockito.MockitoAnnotations.initMocks;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.egit.github.core.client.RequestException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.carlosbecker.github.IntegratorConfig;

public class AppTest {
    private App app;
    @Mock
    private IntegratorConfig config;
    @Mock
    private MainIntegrator integrator;

    @Before
    public void init() {
        initMocks(this);
        app = new App(config, integrator);
    }

    @Test(timeout = 500)
    public void testRunOnce() throws Exception {
        app.run();
    }

    @Test(timeout = 500)
    public void testRunTwice() throws Exception {
        final AtomicBoolean loop = new AtomicBoolean(true);
        Mockito.when(config.loop()).then(invocation -> loop.getAndSet(false));
        app.run();
    }

    @Test(expected = NullPointerException.class)
    public void testMainMethodWithNullArgs() throws Exception {
        App.main(null);
    }

    @Test(expected = NullPointerException.class)
    public void testMainMethodWithNullArgument() throws Exception {
        App.main(new String[] { null });
    }

    @Test(expected = RequestException.class)
    public void testMainMethod() throws Exception {
        App.main(new String[] { "./src/test/resources/main.test.properties" });
    }
}