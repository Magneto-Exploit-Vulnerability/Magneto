/*
 * MIT License
 *
 * <p>Copyright (c) 2021 mixmicro
 *
 * <p>Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * <p>The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * <p>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package xyz.vopen.mixmicro.kits.llc;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * {@link LlcFactory}
 *
 * @author <a href="mailto:siran0611@gmail.com">Elias.Yao</a>
 * @version ${project.version} - 2021/4/13
 */
public enum LlcFactory {
  INSTANCE;
  Map<String, Compress> compressMap = new HashMap<>(14);

  LlcFactory() {
    ServiceLoader<Compress> compresses = ServiceLoader.load(Compress.class);
    for (Compress compress : compresses) {
      Injection spi = compress.getClass().getAnnotation(Injection.class);
      if (spi != null) {
        String name = spi.name();
        if (compressMap.containsKey(name)) {
          throw new RuntimeException(
              "The @Injection value(" + name + ") repeat, for class(" + compress.getClass() + ") and class(" + compressMap.get(name).getClass() + ").");
        }
        compressMap.put(name, compress);
      }
    }
  }

  public Compress getCompressor(String name, LlcContext context) {
    Compress compress = compressMap.get(name);
    compress.injectContext(context);
    return compress;
  }
}