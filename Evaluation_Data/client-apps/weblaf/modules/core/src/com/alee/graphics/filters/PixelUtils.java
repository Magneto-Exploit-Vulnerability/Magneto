/*
 * Copyright 2006 Jerry Huxtable
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alee.graphics.filters;

import com.alee.utils.MathUtils;

import java.awt.*;

/**
 * Some more useful math functions for image processing.
 * These are becoming obsolete as we move to Java2D. Use MiscComposite instead.
 *
 * @author Jerry Huxtable
 */
public final class PixelUtils
{
    public static final int REPLACE = 0;
    public static final int NORMAL = 1;
    public static final int MIN = 2;
    public static final int MAX = 3;
    public static final int ADD = 4;
    public static final int SUBTRACT = 5;
    public static final int DIFFERENCE = 6;
    public static final int MULTIPLY = 7;
    public static final int HUE = 8;
    public static final int SATURATION = 9;
    public static final int VALUE = 10;
    public static final int COLOR = 11;
    public static final int SCREEN = 12;
    public static final int AVERAGE = 13;
    public static final int OVERLAY = 14;
    public static final int CLEAR = 15;
    public static final int EXCHANGE = 16;
    public static final int DISSOLVE = 17;
    public static final int DST_IN = 18;
    public static final int ALPHA = 19;
    public static final int ALPHA_TO_GRAY = 20;

    /**
     * Clamp a value to the range 0..255
     */
    public static int clamp ( final int c )
    {
        if ( c < 0 )
        {
            return 0;
        }
        if ( c > 255 )
        {
            return 255;
        }
        return c;
    }

    public static int interpolate ( final int v1, final int v2, final float f )
    {
        return clamp ( ( int ) ( v1 + f * ( v2 - v1 ) ) );
    }

    public static int brightness ( final int rgb )
    {
        final int r = ( rgb >> 16 ) & 0xff;
        final int g = ( rgb >> 8 ) & 0xff;
        final int b = rgb & 0xff;
        return ( r + g + b ) / 3;
    }

    public static boolean nearColors ( final int rgb1, final int rgb2, final int tolerance )
    {
        final int r1 = ( rgb1 >> 16 ) & 0xff;
        final int g1 = ( rgb1 >> 8 ) & 0xff;
        final int b1 = rgb1 & 0xff;
        final int r2 = ( rgb2 >> 16 ) & 0xff;
        final int g2 = ( rgb2 >> 8 ) & 0xff;
        final int b2 = rgb2 & 0xff;
        return Math.abs ( r1 - r2 ) <= tolerance && Math.abs ( g1 - g2 ) <= tolerance &&
                Math.abs ( b1 - b2 ) <= tolerance;
    }

    private final static float hsb1[] = new float[ 3 ];//FIXME-not thread safe
    private final static float hsb2[] = new float[ 3 ];//FIXME-not thread safe

    // Return rgb1 painted onto rgb2
    public static int combinePixels ( final int rgb1, final int rgb2, final int op )
    {
        return combinePixels ( rgb1, rgb2, op, 0xff );
    }

    public static int combinePixels ( final int rgb1, final int rgb2, final int op, final int extraAlpha, final int channelMask )
    {
        return ( rgb2 & ~channelMask ) | combinePixels ( rgb1 & channelMask, rgb2, op, extraAlpha );
    }

    public static int combinePixels ( int rgb1, final int rgb2, final int op, final int extraAlpha )
    {
        if ( op == REPLACE )
        {
            return rgb1;
        }
        int a1 = ( rgb1 >> 24 ) & 0xff;
        int r1 = ( rgb1 >> 16 ) & 0xff;
        int g1 = ( rgb1 >> 8 ) & 0xff;
        int b1 = rgb1 & 0xff;
        final int a2 = ( rgb2 >> 24 ) & 0xff;
        final int r2 = ( rgb2 >> 16 ) & 0xff;
        final int g2 = ( rgb2 >> 8 ) & 0xff;
        final int b2 = rgb2 & 0xff;

        switch ( op )
        {
            case NORMAL:
                break;
            case MIN:
                r1 = Math.min ( r1, r2 );
                g1 = Math.min ( g1, g2 );
                b1 = Math.min ( b1, b2 );
                break;
            case MAX:
                r1 = Math.max ( r1, r2 );
                g1 = Math.max ( g1, g2 );
                b1 = Math.max ( b1, b2 );
                break;
            case ADD:
                r1 = clamp ( r1 + r2 );
                g1 = clamp ( g1 + g2 );
                b1 = clamp ( b1 + b2 );
                break;
            case SUBTRACT:
                r1 = clamp ( r2 - r1 );
                g1 = clamp ( g2 - g1 );
                b1 = clamp ( b2 - b1 );
                break;
            case DIFFERENCE:
                r1 = clamp ( Math.abs ( r1 - r2 ) );
                g1 = clamp ( Math.abs ( g1 - g2 ) );
                b1 = clamp ( Math.abs ( b1 - b2 ) );
                break;
            case MULTIPLY:
                r1 = clamp ( r1 * r2 / 255 );
                g1 = clamp ( g1 * g2 / 255 );
                b1 = clamp ( b1 * b2 / 255 );
                break;
            case DISSOLVE:
                if ( ( MathUtils.random () & 0xff ) <= a1 )
                {
                    r1 = r2;
                    g1 = g2;
                    b1 = b2;
                }
                break;
            case AVERAGE:
                r1 = ( r1 + r2 ) / 2;
                g1 = ( g1 + g2 ) / 2;
                b1 = ( b1 + b2 ) / 2;
                break;
            case HUE:
            case SATURATION:
            case VALUE:
            case COLOR:
                Color.RGBtoHSB ( r1, g1, b1, hsb1 );
                Color.RGBtoHSB ( r2, g2, b2, hsb2 );
                switch ( op )
                {
                    case HUE:
                        hsb2[ 0 ] = hsb1[ 0 ];
                        break;
                    case SATURATION:
                        hsb2[ 1 ] = hsb1[ 1 ];
                        break;
                    case VALUE:
                        hsb2[ 2 ] = hsb1[ 2 ];
                        break;
                    case COLOR:
                        hsb2[ 0 ] = hsb1[ 0 ];
                        hsb2[ 1 ] = hsb1[ 1 ];
                        break;
                }
                rgb1 = Color.HSBtoRGB ( hsb2[ 0 ], hsb2[ 1 ], hsb2[ 2 ] );
                r1 = ( rgb1 >> 16 ) & 0xff;
                g1 = ( rgb1 >> 8 ) & 0xff;
                b1 = rgb1 & 0xff;
                break;
            case SCREEN:
                r1 = 255 - ( ( 255 - r1 ) * ( 255 - r2 ) ) / 255;
                g1 = 255 - ( ( 255 - g1 ) * ( 255 - g2 ) ) / 255;
                b1 = 255 - ( ( 255 - b1 ) * ( 255 - b2 ) ) / 255;
                break;
            case OVERLAY:
                int m, s;
                s = 255 - ( ( 255 - r1 ) * ( 255 - r2 ) ) / 255;
                m = r1 * r2 / 255;
                r1 = ( s * r1 + m * ( 255 - r1 ) ) / 255;
                s = 255 - ( ( 255 - g1 ) * ( 255 - g2 ) ) / 255;
                m = g1 * g2 / 255;
                g1 = ( s * g1 + m * ( 255 - g1 ) ) / 255;
                s = 255 - ( ( 255 - b1 ) * ( 255 - b2 ) ) / 255;
                m = b1 * b2 / 255;
                b1 = ( s * b1 + m * ( 255 - b1 ) ) / 255;
                break;
            case CLEAR:
                r1 = g1 = b1 = 0xff;
                break;
            case DST_IN:
                r1 = clamp ( ( r2 * a1 ) / 255 );
                g1 = clamp ( ( g2 * a1 ) / 255 );
                b1 = clamp ( ( b2 * a1 ) / 255 );
                a1 = clamp ( ( a2 * a1 ) / 255 );
                return ( a1 << 24 ) | ( r1 << 16 ) | ( g1 << 8 ) | b1;
            case ALPHA:
                a1 = a1 * a2 / 255;
                return ( a1 << 24 ) | ( r2 << 16 ) | ( g2 << 8 ) | b2;
            case ALPHA_TO_GRAY:
                final int na = 255 - a1;
                return ( a1 << 24 ) | ( na << 16 ) | ( na << 8 ) | na;
        }
        if ( extraAlpha != 0xff || a1 != 0xff )
        {
            a1 = a1 * extraAlpha / 255;
            final int a3 = ( 255 - a1 ) * a2 / 255;
            r1 = clamp ( ( r1 * a1 + r2 * a3 ) / 255 );
            g1 = clamp ( ( g1 * a1 + g2 * a3 ) / 255 );
            b1 = clamp ( ( b1 * a1 + b2 * a3 ) / 255 );
            a1 = clamp ( a1 + a3 );
        }
        return ( a1 << 24 ) | ( r1 << 16 ) | ( g1 << 8 ) | b1;
    }
}