from jsmin import jsmin
import sys

if len(sys.argv) < 3:
    print("Usage: python jsminify.py <sourcePath> <minifiedPath>")
    sys.exit(1)

source_path = sys.argv[1]
minified_path = sys.argv[2]

with open(source_path, 'r') as js_file:
    minified = jsmin(js_file.read())

with open(minified_path, 'w') as minified_file:
    minified_file.write(minified)

print(f"Minification done. Output at {minified_path}")