import pathlib
import platform
import os
import stat

text = '''#!/bin/sh
# update leaderboard before commit
python3 leaderboard.py
git add leaderboard.json
'''

if platform.system() == 'Windows':
    text = text.replace('python3', 'python')

path = pathlib.Path('.git/hooks/pre-commit')
fp = open(path, 'w')
fp.write(text)
fp.close()

if platform.system() != 'Windows':
    st = os.stat(path)
    os.chmod(path, st.st_mode | stat.S_IEXEC)


print("Hooks setup finished!")
