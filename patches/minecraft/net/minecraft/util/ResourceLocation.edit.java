
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  7 : 8  @  7

+ 	public String resourceName;

> INSERT  1 : 8  @  1

+ 	public Object cachedPointer = null;
+ 	public int cachedPointerType = 0;
+ 
+ 	public static final int CACHED_POINTER_NONE = 0;
+ 	public static final int CACHED_POINTER_TEXTURE = 1;
+ 	public static final int CACHED_POINTER_EAGLER_MESH = 2;
+ 

> INSERT  1 : 2  @  1

+ 		this.resourceName = resourceName[0];

> INSERT  8 : 9  @  8

+ 		this.resourceName = resourceName;

> EOF
