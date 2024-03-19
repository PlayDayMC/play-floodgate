/*
 * Copyright (c) 2019-2022 GeyserMC. http://geysermc.org
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Floodgate
 */

package org.geysermc.floodgate.core.util;

public final class Constants {
    public static final int METRICS_ID = 14649;

    public static final int CONFIG_VERSION = 3;

    public static final int PROTOCOL_HEX_COLOR = 713; // added in 20w17a (1.16 snapshot)

    public static final int MAX_DEBUG_PACKET_COUNT = 20;
    public static final boolean DEBUG_MODE = false;
    public static final boolean PRINT_ALL_PACKETS = false;


    public static final String USER_AGENT = "GeyserMC/Floodgate";
    private static final String API_BASE_URL = "s://api.geysermc.org";
    public static final String HEALTH_URL = "http" + API_BASE_URL + "/health";

    public static final String LINK_INFO_URL = "https://link.geysermc.org/";
    public static final String LATEST_DOWNLOAD_URL = "https://geysermc.org/download";

    public static final String INTERNAL_ERROR_MESSAGE =
            "An internal error happened while handling Floodgate data." +
            " Try logging in again or contact a server administrator if the issue persists.";
    public static final String UNSUPPORTED_DATA_VERSION =
            "Received an unsupported Floodgate data version." +
            " This Floodgate version is made for data version %s, received %s." +
            " Make sure that Floodgate is up-to-date.";


    public static final int HANDSHAKE_PACKET_ID = 0;
    public static final int LOGIN_SUCCESS_PACKET_ID = 2;
    public static final int SET_COMPRESSION_PACKET_ID = 3;
}
