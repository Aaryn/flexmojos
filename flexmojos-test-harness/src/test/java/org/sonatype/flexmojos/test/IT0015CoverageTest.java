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

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.io.File;

import org.sonatype.flexmojos.tests.AbstractFlexMojosTests;
import org.testng.annotations.Test;

public class IT0015CoverageTest
    extends AbstractFlexMojosTests
{

    public void standardConceptTester( String coverageName )
        throws Exception
    {
        File testDir = getProject( "/coverage/" + coverageName );
        test( testDir, "install" );
    }

    @Test( timeOut = 120000 )
    public void testSourceFileResolver()
        throws Exception
    {
        standardConceptTester( "source-file-resolver" );
    }

    @Test( timeOut = 120000 )
    public void testAsdocInclusionExclusion()
        throws Exception
    {
        File testDir = getProject( "/coverage/asdoc-inclusion-exclusion" );
        test( testDir, "org.sonatype.flexmojos:flex-maven-plugin:" + getProperty( "version" ) + ":asdoc" );
        File vermelho = new File( testDir, "target/asdoc/Vermelho.html" );
        assertFalse( "Should not generate Vermelho.html.", vermelho.exists() );
        File amarelo = new File( testDir, "target/asdoc/Amarelo.html" );
        assertFalse( "Should not generate Amarelo.html.", amarelo.exists() );
    }

    @Test( timeOut = 120000 )
    public void testFlexUnitReport()
        throws Exception
    {
        File testDir = getProject( "/concept/flexunit-example" );
        test( testDir, "site:site" );
        File asdoc = new File( testDir, "/target/site/asdoc" );
        assertTrue( asdoc.isDirectory() );
    }

    @Test( timeOut = 120000 )
    public void testHtmlwrapperTemplates()
        throws Exception
    {
        File testDir = getProject( "/coverage/htmlwrapper-templates" );
        test( testDir, "package" );
        File folder = new File( testDir, "folder/target/htmlwrapper-templates-folder-1.0-SNAPSHOT.html" );
        assertTrue( "Should generate htmlwrapper" + folder.getAbsolutePath(), folder.exists() );
        File zip = new File( testDir, "zip/target/htmlwrapper-templates-zip-1.0-SNAPSHOT.html" );
        assertTrue( "Should generate htmlwrapper " + zip.getAbsolutePath(), zip.exists() );
    }

    @Test( timeOut = 120000 )
    public void testDefines()
        throws Exception
    {
        File testDir = getProject( "/issues/issue-0068" );
        test( testDir, "install" );

    }

    @Test( timeOut = 120000 )
    public void testCompilationOptions()
        throws Exception
    {
        standardConceptTester( "compilation-options" );
    }

}
