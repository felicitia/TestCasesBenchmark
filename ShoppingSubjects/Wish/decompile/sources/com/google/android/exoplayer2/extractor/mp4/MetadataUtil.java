package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

final class MetadataUtil {
    private static final int SHORT_TYPE_ALBUM = Util.getIntegerCodeForString("alb");
    private static final int SHORT_TYPE_ARTIST = Util.getIntegerCodeForString("ART");
    private static final int SHORT_TYPE_COMMENT = Util.getIntegerCodeForString("cmt");
    private static final int SHORT_TYPE_COMPOSER_1 = Util.getIntegerCodeForString("com");
    private static final int SHORT_TYPE_COMPOSER_2 = Util.getIntegerCodeForString("wrt");
    private static final int SHORT_TYPE_ENCODER = Util.getIntegerCodeForString("too");
    private static final int SHORT_TYPE_GENRE = Util.getIntegerCodeForString("gen");
    private static final int SHORT_TYPE_LYRICS = Util.getIntegerCodeForString("lyr");
    private static final int SHORT_TYPE_NAME_1 = Util.getIntegerCodeForString("nam");
    private static final int SHORT_TYPE_NAME_2 = Util.getIntegerCodeForString("trk");
    private static final int SHORT_TYPE_YEAR = Util.getIntegerCodeForString("day");
    private static final String[] STANDARD_GENRES = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};
    private static final int TYPE_ALBUM_ARTIST = Util.getIntegerCodeForString("aART");
    private static final int TYPE_COMPILATION = Util.getIntegerCodeForString("cpil");
    private static final int TYPE_COVER_ART = Util.getIntegerCodeForString("covr");
    private static final int TYPE_DISK_NUMBER = Util.getIntegerCodeForString("disk");
    private static final int TYPE_GAPLESS_ALBUM = Util.getIntegerCodeForString("pgap");
    private static final int TYPE_GENRE = Util.getIntegerCodeForString("gnre");
    private static final int TYPE_GROUPING = Util.getIntegerCodeForString("grp");
    private static final int TYPE_INTERNAL = Util.getIntegerCodeForString("----");
    private static final int TYPE_RATING = Util.getIntegerCodeForString("rtng");
    private static final int TYPE_SORT_ALBUM = Util.getIntegerCodeForString("soal");
    private static final int TYPE_SORT_ALBUM_ARTIST = Util.getIntegerCodeForString("soaa");
    private static final int TYPE_SORT_ARTIST = Util.getIntegerCodeForString("soar");
    private static final int TYPE_SORT_COMPOSER = Util.getIntegerCodeForString("soco");
    private static final int TYPE_SORT_TRACK_NAME = Util.getIntegerCodeForString("sonm");
    private static final int TYPE_TEMPO = Util.getIntegerCodeForString("tmpo");
    private static final int TYPE_TRACK_NUMBER = Util.getIntegerCodeForString("trkn");
    private static final int TYPE_TV_SHOW = Util.getIntegerCodeForString("tvsh");
    private static final int TYPE_TV_SORT_SHOW = Util.getIntegerCodeForString("sosn");

    public static Entry parseIlstElement(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition() + parsableByteArray.readInt();
        int readInt = parsableByteArray.readInt();
        int i = (readInt >> 24) & 255;
        if (i == 169 || i == 65533) {
            int i2 = 16777215 & readInt;
            if (i2 == SHORT_TYPE_COMMENT) {
                CommentFrame parseCommentAttribute = parseCommentAttribute(readInt, parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseCommentAttribute;
            }
            if (i2 != SHORT_TYPE_NAME_1) {
                if (i2 != SHORT_TYPE_NAME_2) {
                    if (i2 != SHORT_TYPE_COMPOSER_1) {
                        if (i2 != SHORT_TYPE_COMPOSER_2) {
                            if (i2 == SHORT_TYPE_YEAR) {
                                TextInformationFrame parseTextAttribute = parseTextAttribute(readInt, "TDRC", parsableByteArray);
                                parsableByteArray.setPosition(position);
                                return parseTextAttribute;
                            } else if (i2 == SHORT_TYPE_ARTIST) {
                                TextInformationFrame parseTextAttribute2 = parseTextAttribute(readInt, "TPE1", parsableByteArray);
                                parsableByteArray.setPosition(position);
                                return parseTextAttribute2;
                            } else if (i2 == SHORT_TYPE_ENCODER) {
                                TextInformationFrame parseTextAttribute3 = parseTextAttribute(readInt, "TSSE", parsableByteArray);
                                parsableByteArray.setPosition(position);
                                return parseTextAttribute3;
                            } else if (i2 == SHORT_TYPE_ALBUM) {
                                TextInformationFrame parseTextAttribute4 = parseTextAttribute(readInt, "TALB", parsableByteArray);
                                parsableByteArray.setPosition(position);
                                return parseTextAttribute4;
                            } else if (i2 == SHORT_TYPE_LYRICS) {
                                TextInformationFrame parseTextAttribute5 = parseTextAttribute(readInt, "USLT", parsableByteArray);
                                parsableByteArray.setPosition(position);
                                return parseTextAttribute5;
                            } else if (i2 == SHORT_TYPE_GENRE) {
                                TextInformationFrame parseTextAttribute6 = parseTextAttribute(readInt, "TCON", parsableByteArray);
                                parsableByteArray.setPosition(position);
                                return parseTextAttribute6;
                            } else if (i2 == TYPE_GROUPING) {
                                TextInformationFrame parseTextAttribute7 = parseTextAttribute(readInt, "TIT1", parsableByteArray);
                                parsableByteArray.setPosition(position);
                                return parseTextAttribute7;
                            }
                        }
                    }
                    TextInformationFrame parseTextAttribute8 = parseTextAttribute(readInt, "TCOM", parsableByteArray);
                    parsableByteArray.setPosition(position);
                    return parseTextAttribute8;
                }
            }
            TextInformationFrame parseTextAttribute9 = parseTextAttribute(readInt, "TIT2", parsableByteArray);
            parsableByteArray.setPosition(position);
            return parseTextAttribute9;
        }
        try {
            if (readInt == TYPE_GENRE) {
                return parseStandardGenreAttribute(parsableByteArray);
            }
            if (readInt == TYPE_DISK_NUMBER) {
                TextInformationFrame parseIndexAndCountAttribute = parseIndexAndCountAttribute(readInt, "TPOS", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseIndexAndCountAttribute;
            } else if (readInt == TYPE_TRACK_NUMBER) {
                TextInformationFrame parseIndexAndCountAttribute2 = parseIndexAndCountAttribute(readInt, "TRCK", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseIndexAndCountAttribute2;
            } else if (readInt == TYPE_TEMPO) {
                Id3Frame parseUint8Attribute = parseUint8Attribute(readInt, "TBPM", parsableByteArray, true, false);
                parsableByteArray.setPosition(position);
                return parseUint8Attribute;
            } else if (readInt == TYPE_COMPILATION) {
                Id3Frame parseUint8Attribute2 = parseUint8Attribute(readInt, "TCMP", parsableByteArray, true, true);
                parsableByteArray.setPosition(position);
                return parseUint8Attribute2;
            } else if (readInt == TYPE_COVER_ART) {
                ApicFrame parseCoverArt = parseCoverArt(parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseCoverArt;
            } else if (readInt == TYPE_ALBUM_ARTIST) {
                TextInformationFrame parseTextAttribute10 = parseTextAttribute(readInt, "TPE2", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute10;
            } else if (readInt == TYPE_SORT_TRACK_NAME) {
                TextInformationFrame parseTextAttribute11 = parseTextAttribute(readInt, "TSOT", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute11;
            } else if (readInt == TYPE_SORT_ALBUM) {
                TextInformationFrame parseTextAttribute12 = parseTextAttribute(readInt, "TSO2", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute12;
            } else if (readInt == TYPE_SORT_ARTIST) {
                TextInformationFrame parseTextAttribute13 = parseTextAttribute(readInt, "TSOA", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute13;
            } else if (readInt == TYPE_SORT_ALBUM_ARTIST) {
                TextInformationFrame parseTextAttribute14 = parseTextAttribute(readInt, "TSOP", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute14;
            } else if (readInt == TYPE_SORT_COMPOSER) {
                TextInformationFrame parseTextAttribute15 = parseTextAttribute(readInt, "TSOC", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute15;
            } else if (readInt == TYPE_RATING) {
                Id3Frame parseUint8Attribute3 = parseUint8Attribute(readInt, "ITUNESADVISORY", parsableByteArray, false, false);
                parsableByteArray.setPosition(position);
                return parseUint8Attribute3;
            } else if (readInt == TYPE_GAPLESS_ALBUM) {
                Id3Frame parseUint8Attribute4 = parseUint8Attribute(readInt, "ITUNESGAPLESS", parsableByteArray, false, true);
                parsableByteArray.setPosition(position);
                return parseUint8Attribute4;
            } else if (readInt == TYPE_TV_SORT_SHOW) {
                TextInformationFrame parseTextAttribute16 = parseTextAttribute(readInt, "TVSHOWSORT", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute16;
            } else if (readInt == TYPE_TV_SHOW) {
                TextInformationFrame parseTextAttribute17 = parseTextAttribute(readInt, "TVSHOW", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute17;
            } else if (readInt == TYPE_INTERNAL) {
                Id3Frame parseInternalAttribute = parseInternalAttribute(parsableByteArray, position);
                parsableByteArray.setPosition(position);
                return parseInternalAttribute;
            }
        } finally {
            parsableByteArray.setPosition(position);
        }
        String str = "MetadataUtil";
        StringBuilder sb = new StringBuilder();
        sb.append("Skipped unknown metadata entry: ");
        sb.append(Atom.getAtomTypeString(readInt));
        Log.d(str, sb.toString());
        parsableByteArray.setPosition(position);
        return null;
    }

    private static TextInformationFrame parseTextAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            return new TextInformationFrame(str, null, parsableByteArray.readNullTerminatedString(readInt - 16));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Failed to parse text attribute: ");
        sb.append(Atom.getAtomTypeString(i));
        Log.w("MetadataUtil", sb.toString());
        return null;
    }

    private static CommentFrame parseCommentAttribute(int i, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            String readNullTerminatedString = parsableByteArray.readNullTerminatedString(readInt - 16);
            return new CommentFrame("und", readNullTerminatedString, readNullTerminatedString);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Failed to parse comment attribute: ");
        sb.append(Atom.getAtomTypeString(i));
        Log.w("MetadataUtil", sb.toString());
        return null;
    }

    private static Id3Frame parseUint8Attribute(int i, String str, ParsableByteArray parsableByteArray, boolean z, boolean z2) {
        Id3Frame id3Frame;
        int parseUint8AttributeValue = parseUint8AttributeValue(parsableByteArray);
        if (z2) {
            parseUint8AttributeValue = Math.min(1, parseUint8AttributeValue);
        }
        if (parseUint8AttributeValue >= 0) {
            if (z) {
                id3Frame = new TextInformationFrame(str, null, Integer.toString(parseUint8AttributeValue));
            } else {
                id3Frame = new CommentFrame("und", str, Integer.toString(parseUint8AttributeValue));
            }
            return id3Frame;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Failed to parse uint8 attribute: ");
        sb.append(Atom.getAtomTypeString(i));
        Log.w("MetadataUtil", sb.toString());
        return null;
    }

    private static TextInformationFrame parseIndexAndCountAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data && readInt >= 22) {
            parsableByteArray.skipBytes(10);
            int readUnsignedShort = parsableByteArray.readUnsignedShort();
            if (readUnsignedShort > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(readUnsignedShort);
                String sb2 = sb.toString();
                int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
                if (readUnsignedShort2 > 0) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(sb2);
                    sb3.append("/");
                    sb3.append(readUnsignedShort2);
                    sb2 = sb3.toString();
                }
                return new TextInformationFrame(str, null, sb2);
            }
        }
        StringBuilder sb4 = new StringBuilder();
        sb4.append("Failed to parse index/count attribute: ");
        sb4.append(Atom.getAtomTypeString(i));
        Log.w("MetadataUtil", sb4.toString());
        return null;
    }

    private static TextInformationFrame parseStandardGenreAttribute(ParsableByteArray parsableByteArray) {
        int parseUint8AttributeValue = parseUint8AttributeValue(parsableByteArray);
        String str = (parseUint8AttributeValue <= 0 || parseUint8AttributeValue > STANDARD_GENRES.length) ? null : STANDARD_GENRES[parseUint8AttributeValue - 1];
        if (str != null) {
            return new TextInformationFrame("TCON", null, str);
        }
        Log.w("MetadataUtil", "Failed to parse standard genre code");
        return null;
    }

    private static ApicFrame parseCoverArt(ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            int parseFullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
            String str = parseFullAtomFlags == 13 ? "image/jpeg" : parseFullAtomFlags == 14 ? "image/png" : null;
            if (str == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unrecognized cover art flags: ");
                sb.append(parseFullAtomFlags);
                Log.w("MetadataUtil", sb.toString());
                return null;
            }
            parsableByteArray.skipBytes(4);
            byte[] bArr = new byte[(readInt - 16)];
            parsableByteArray.readBytes(bArr, 0, bArr.length);
            return new ApicFrame(str, null, 3, bArr);
        }
        Log.w("MetadataUtil", "Failed to parse cover art attribute");
        return null;
    }

    private static Id3Frame parseInternalAttribute(ParsableByteArray parsableByteArray, int i) {
        Object obj = null;
        String str = null;
        int i2 = -1;
        int i3 = -1;
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            if (readInt2 == Atom.TYPE_mean) {
                obj = parsableByteArray.readNullTerminatedString(readInt - 12);
            } else if (readInt2 == Atom.TYPE_name) {
                str = parsableByteArray.readNullTerminatedString(readInt - 12);
            } else {
                if (readInt2 == Atom.TYPE_data) {
                    i2 = position;
                    i3 = readInt;
                }
                parsableByteArray.skipBytes(readInt - 12);
            }
        }
        if (!"com.apple.iTunes".equals(obj) || !"iTunSMPB".equals(str) || i2 == -1) {
            return null;
        }
        parsableByteArray.setPosition(i2);
        parsableByteArray.skipBytes(16);
        return new CommentFrame("und", str, parsableByteArray.readNullTerminatedString(i3 - 16));
    }

    private static int parseUint8AttributeValue(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            return parsableByteArray.readUnsignedByte();
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }
}
