/**
 * Flexmojos is a set of maven goals to allow maven users to compile, optimize and test Flex SWF, Flex SWC, Air SWF and Air SWC.
 * Copyright (C) 2008-2012  Marvin Froeder <marvin@flexmojos.net>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sonatype.flexmojos.test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.FileReader;

import org.apache.maven.it.VerificationException;
import org.sonatype.flexmojos.test.report.TestCaseReport;
import org.sonatype.flexmojos.test.util.XStreamFactory;
import org.sonatype.flexmojos.tests.AbstractFlexMojosTests;
import org.testng.annotations.Test;

import com.thoughtworks.xstream.XStream;

public class IT0013IssuesTest
    extends AbstractFlexMojosTests
{

    public void testIssue( String issueNumber )
        throws Exception
    {
        File testDir = getProject( "/issues/" + issueNumber );
        test( testDir, "install" );
    }

    @Test( timeOut = 120000 )
    public void issue11()
        throws Exception
    {
        testIssue( "issue-0011" );
    }

    @Test( timeOut = 120000 )
    public void issue13()
        throws Exception
    {
        File testDir = getProject( "/issues/issue-0013" );
        test( testDir, "install" );

        File reportDir = new File( testDir, "target/surefire-reports" );
        assertEquals( 2, reportDir.listFiles().length );
    }

    @Test( timeOut = 120000, expectedExceptions = { VerificationException.class } )
    public void issue14()
        throws Exception
    {
        testIssue( "issue-0014" );
    }

    @Test( timeOut = 120000, expectedExceptions = { VerificationException.class } )
    public void issue15()
        throws Exception
    {
        File testDir = getProject( "/issues/issue-0015" );
        try
        {
            test( testDir, "install" );
        }
        finally
        {
            File reportDir = new File( testDir, "target/surefire-reports" );
            assertEquals( 2, reportDir.listFiles().length );

            XStream xs = XStreamFactory.getXStreamInstance();
            File reportFile = new File( reportDir, "TEST-com.adobe.example.TestCalculator.xml" );
            TestCaseReport report = (TestCaseReport) xs.fromXML( new FileReader( reportFile ) );

            assertEquals( "com.adobe.example.TestCalculator", report.getName() );
            assertEquals( 2, report.getTests() );
            assertEquals( 1, report.getErrors() );
        }

    }

    @Test( timeOut = 120000 )
    public void issue17()
        throws Exception
    {
        File testDir = getProject( "/issues/issue-0017" );
        test( testDir, "site" );

        File asdoc = new File( testDir, "target/site/asdoc" );
        assertTrue( "asdoc directory must exist", asdoc.isDirectory() );
    }

    @Test( timeOut = 120000, expectedExceptions = { VerificationException.class } )
    public void issue27()
        throws Exception
    {
        File testDir = getProject( "/issues/issue-0027" );
        test( testDir, "org.sonatype.flexmojos:flex-maven-plugin:" + getProperty( "version" ) + ":asdoc" );
    }

    @Test( timeOut = 120000 )
    public void issue29()
        throws Exception
    {
        testIssue( "issue-0029" );
    }

    @Test( timeOut = 120000 )
    public void issue32()
        throws Exception
    {
        testIssue( "issue-0032" );
    }

    @Test( timeOut = 120000 )
    public void issue39()
        throws Exception
    {
        testIssue( "issue-0039" );
    }

    // A wierd but on this tests
    // @Test(timeOut=120000) public void issue43() throws Exception {
    // File testDir = ResourceExtractor.simpleExtractResources(getClass(),
    // "/issues/issue-0014");
    // List<String> args = new ArrayList<String>();
    // args.add("-Dmaven.test.failure.ignore=true");
    // test(testDir, "info.rvin.itest.issues", "issue-0014",
    // "1.0-SNAPSHOT", "swf", "install", args);
    // }

    @Test( timeOut = 120000, expectedExceptions = { VerificationException.class } )
    public void issue44()
        throws Exception
    {
        File testDir = getProject( "/issues/issue-0044" );
        test( testDir, "org.sonatype.flexmojos:flex-maven-plugin:" + getProperty( "version" ) + ":asdoc" );
    }

    @Test( timeOut = 120000 )
    public void issue53()
        throws Exception
    {
        File testDir = getProject( "/issues/issue-0014" );
        test( testDir, "install", "-Dmaven.test.skip=true" );

        test( testDir, "install", "-DskipTests=true" );
    }

    @Test( timeOut = 120000, groups = { "generator" } )
    public void issue61()
        throws Exception
    {
        testIssue( "issue-0061" );
    }

    @Test( timeOut = 120000 )
    public void issue67()
        throws Exception
    {
        File testDir = getProject( "/issues/issue-0067" );
        test( testDir, "org.sonatype.flexmojos:flex-maven-plugin:" + getProperty( "version" ) + ":asdoc" );
    }

    @Test( timeOut = 120000 )
    public void issue68()
        throws Exception
    {
        File testDir = getProject( "/issues/issue-0068" );
        test( testDir, "org.sonatype.flexmojos:flex-maven-plugin:" + getProperty( "version" ) + ":asdoc" );
    }

    @Test( timeOut = 120000 )
    public void issue70()
        throws Exception
    {
        testIssue( "issue-0070" );
    }

    @Test( timeOut = 120000 )
    public void issue103()
        throws Exception
    {
        testIssue( "issue-0103/project" );
        // TODO check SWC content
    }

}
