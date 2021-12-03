text = '''# update leaderboard before commit
python3 leaderboard.py
git add leaderboard.json
'''

fp = open('.git/hooks/pre-commit', 'w')
fp.write(text)
fp.close()

print("Hooks setup finished!")
