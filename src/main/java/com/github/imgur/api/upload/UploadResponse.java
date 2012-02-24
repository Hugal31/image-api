/*
 * Copyright (C) 2010 David Wursteisen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.imgur.api.upload;

import com.github.commons.Response;
import com.google.gson.annotations.SerializedName;

public class UploadResponse implements Response {
    /*
    {
    "upload": {
        "image": {
            "name": false,
            "title": "",
            "caption": "",
            "hash": "cSNjk",
            "deletehash": "ZnKGru1reZKoabU",
            "datetime": "2010-08-16 22:43:22",
            "type": "image\/jpeg",
            "animated": "false",
            "width": 720,
            "height": 540,
            "size": 46174,
            "views": 0,
            "bandwidth": 0
        },
        "links": {
            "original": "http:\/\/imgur.com\/cSNjk.jpg",
            "imgur_page": "http:\/\/imgur.com\/cSNjk",
            "delete_page": "http:\/\/imgur.com\/delete\/ZnKGru1reZKoabU",
            "small_square": "http:\/\/imgur.com\/cSNjks.jpg",
            "large_thumbnail": "http:\/\/imgur.com\/cSNjkl.jpg"
        }
    }
}
     */

    private Upload upload = new Upload();

    public Links getLinks() {
        return upload.links;
    }

    public Image getImage() {
        return upload.image;
    }

    static private class Upload {
        private Links links;
        private Image image;
    }

    static public class Image {
        private boolean name;
        private String title;
        private String caption;
        private String hash;
        private String deleteHash;
        private String datetime;
        private String type;
        private String animated;
        private int width;
        private int height;
        private int size;
        private int view;
        private int bandwidth;

        public boolean isName() {
            return name;
        }

        public String getTitle() {
            return title;
        }

        public String getCaption() {
            return caption;
        }

        public String getHash() {
            return hash;
        }

        public String getDeleteHash() {
            return deleteHash;
        }

        public String getDatetime() {
            return datetime;
        }

        public String getType() {
            return type;
        }

        public String getAnimated() {
            return animated;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getSize() {
            return size;
        }

        public int getView() {
            return view;
        }

        public int getBandwidth() {
            return bandwidth;
        }
    }

    static public class Links {
        private String original;
        @SerializedName("imgur_page")
        private String imgurPage;
        @SerializedName("delete_page")
        private String deletePage;
        @SerializedName("small_square")
        private String smallSquare;
        @SerializedName("large_thumbnail")
        private String largeThumbnail;

        public String getOriginal() {
            return original;
        }

        public String getImgurPage() {
            return imgurPage;
        }

        public String getDeletePage() {
            return deletePage;
        }

        public String getSmallSquare() {
            return smallSquare;
        }

        public String getLargeThumbnail() {
            return largeThumbnail;
        }
    }

    @Override
    public String toString() {
        return "{upload : delete links "+getLinks().getDeletePage() + " }";
    }
}
