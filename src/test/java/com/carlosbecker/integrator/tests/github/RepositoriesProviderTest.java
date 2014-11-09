/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Carlos Alexandro Becker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.carlosbecker.integrator.tests.github;

import com.carlosbecker.guice.GuiceModules;
import com.carlosbecker.guice.GuiceTestRunner;
import com.carlosbecker.integrator.github.GithubModule;
import com.carlosbecker.integrator.model.ScriptedRepositories;
import com.carlosbecker.integrator.model.ScriptedRepository;
import com.carlosbecker.integrator.tests.ConfigLoader;
import javax.inject.Inject;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test the repository provider.
 * @author Carlos Alexandro Becker (caarlos0@gmail.com)
 * @version $Id$
 */
@RunWith(GuiceTestRunner.class)
@GuiceModules(GithubModule.class)
public class RepositoriesProviderTest {
    /**
     * Rule.
     * @checkstyle VisibilityModifierCheck (5 lines)
     */
    @ClassRule
    public static ConfigLoader configLoader = new ConfigLoader();

    /**
     * Scripted Repositories
     */
    @Inject
    private ScriptedRepositories repositories;

    /**
     * Test it was injected.
     * @throws Exception
     */
    @Test
    public final void testProvided() throws Exception {
        Assert.assertThat(this.repositories, CoreMatchers.notNullValue());
    }

    /**
     * Test it was correctly parsed.
     * @throws Exception
     */
    @Test
    public final void testCorrectParsing() throws Exception {
        final ScriptedRepository repository = this.repositories.iterator()
            .next();
        Assert.assertThat(
            repository.getId().getOwner(),
            CoreMatchers.equalTo("caarlos0")
            );
        Assert.assertThat(
            repository.getId().getName(),
            CoreMatchers.equalTo("github-integrator")
            );
    }
}
