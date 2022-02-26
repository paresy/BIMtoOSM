package de.rebsc.bimtoosm.loader

/******************************************************************************
 * Copyright (C) 2021  de.rebsc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see {@literal<http://www.gnu.org/licenses/>}.
 *****************************************************************************/

import de.rebsc.bimtoosm.exception.BIMtoOSMException
import org.bimserver.plugins.deserializers.DeserializeException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class LoaderTest {

    @Test
    fun loadFileTest() {
        val dir = System.getProperty("user.dir")

        //------------ test invalid file ------------ //
        // test non-existent ifc file
        Assertions.assertThrows(BIMtoOSMException::class.java) {
            val filepath = "$dir/src/test/resources/ifc4/invalidFilePath.ifc".replace("/", File.separator)
            Loader.loadIntoModel(filepath)
        }

        // test invalid file with block comments
        Assertions.assertThrows(DeserializeException::class.java) {
            val filepath = "$dir/src/test/resources/ifc2x3tc1/house_1_IFC2X3TC1_BC.ifc".replace("/", File.separator)
            Loader.loadIntoModel(filepath)
        }

        // TODO test more invalid ifc file

        //------------ test valid file ------------ //
        // test valid IFC4 file
        Assertions.assertDoesNotThrow {
            val filepath = "$dir/src/test/resources/ifc4/kfz_house_IFC4.ifc".replace("/", File.separator)
            Loader.loadIntoModel(filepath)
        }

        // test valid IFC2X3_TC1 file
        Assertions.assertDoesNotThrow {
            val filepath = "$dir/src/test/resources/ifc2x3tc1/house_1_IFC2X3TC1.ifc".replace("/", File.separator)
            Loader.loadIntoModel(filepath)
        }

    }

}