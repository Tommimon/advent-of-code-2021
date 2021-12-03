import pathlib

text = '''# update leaderboard before commit
python3 leaderboard.py
git add leaderboard.json
'''

path = pathlib.Path('.git/hooks/pre-commit')
fp = open(path, 'w')
fp.write(text)
fp.close()

print("Hooks setup finished!")
