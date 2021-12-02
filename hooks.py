fp = open('.git/hooks/pre-commit', 'w')
fp.write('# update leaderboard before commit\npython3 leaderboard.py')
fp.close()

print("Hooks setup finished!")
