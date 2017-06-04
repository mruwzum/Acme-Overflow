/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package utilities.internal;

import java.io.IOException;
import java.io.OutputStream;

public class EclipseStream extends OutputStream {

    // Constructors -----------------------------------------------------------

    private static OutputStream lastStream;


    // Internal state ---------------------------------------------------------
    private final OutputStream target;

    public EclipseStream(final OutputStream originalStream) {
        assert originalStream != null;

        this.target = originalStream;
    }


    // OutputStream interface -------------------------------------------------

    @Override
    public void close() throws IOException {
        this.target.close();
    }

    @Override
    public void flush() throws IOException {
        this.target.flush();
    }

    @Override
    public void write(final byte[] buffer) throws IOException {
        assert buffer != null;

        this.swap();
        this.target.write(buffer);
    }

    @Override
    public void write(final byte[] buffer, final int offset, final int length) throws IOException {
        assert buffer != null;
        assert offset >= 0 && offset < buffer.length;
        assert offset + length - 1 < buffer.length;

        this.swap();
        this.target.write(buffer, offset, length);
    }

    @Override
    public void write(final int datum) throws IOException {
        this.swap();
        this.target.write(datum);
    }

    // Ancillary methods ------------------------------------------------------
    protected void swap() throws IOException {
        if (EclipseStream.lastStream != this && EclipseStream.lastStream != null) {
            EclipseStream.lastStream.flush();
            try {
                Thread.sleep(250);
            } catch (final InterruptedException oops) {
            }
        }
        EclipseStream.lastStream = this;
    }
}
