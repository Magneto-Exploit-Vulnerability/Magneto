/*
 * Copyright 2015 Lithium Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lithium.flow.config.loaders;

import static com.google.common.base.Preconditions.checkNotNull;

import com.lithium.flow.config.ConfigLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Matt Ayres
 */
public class FileConfigLoader implements ConfigLoader {
	private final String root;

	public FileConfigLoader() {
		this(null);
	}

	public FileConfigLoader(@Nullable String root) {
		this.root = root;
	}

	@Override
	@Nullable
	public InputStream getInputStream(@Nonnull String path) throws IOException {
		checkNotNull(path);
		File file = new File(root == null ? path : root + "/" + path);
		return file.exists() ? new FileInputStream(file) : null;
	}
}