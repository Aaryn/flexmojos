/**
 *  Copyright 2008 Marvin Herman Froeder
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 */
package info.flexmojos.tests.issues;

import java.io.File;
import java.io.IOException;

import org.apache.maven.it.VerificationException;
import org.junit.Assert;
import org.testng.annotations.Test;

public class Issue0152Test
    extends AbstractIssueTest
{

    @Test( timeOut = 120000 )
    public void issue152()
        throws Exception
    {
        long off = run( false );
        long on = run( true );
        Assert.assertTrue( "loadExterns should reduce module size " + on + "/" + off, on < off );
    }

    private long run( boolean isLoadExterns )
        throws IOException, VerificationException
    {
        File testDir = getProject( "/issues/issue-0152" );
        test( testDir, "install", "-DloadExternsOnModules=" + isLoadExterns );

        File module = new File( testDir, "target/issue-0152-1.0-SNAPSHOT-AnModule.swf" );

        return module.length();
    }

}
