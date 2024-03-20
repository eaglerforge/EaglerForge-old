#!/bin/bash

pip install jsmin
python desktopRuntime/minifier.py javascript/classes.js javascript/classes.js
read -p "Press [Enter] key to continue..."