/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.iconset;

import com.alee.api.resource.ClassResource;
import com.alee.managers.icon.set.XmlIconSet;

/**
 * Dark WebLaF icon set.
 *
 * @author Mikle Garin
 */
public final class DarkIconSet extends XmlIconSet
{
    /**
     * Constructs new {@link DarkIconSet}.
     */
    public DarkIconSet ()
    {
        super ( new ClassResource (  DarkIconSet.class, "resources/dark-icon-set.xml" ) );
    }
}