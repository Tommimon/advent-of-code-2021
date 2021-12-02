from os.path import exists
import requests

cookieFile = 'cookies.py'
leaderboardFile = 'leaderboard.json'
url = 'https://adventofcode.com/2021/leaderboard/private/view/976110.json'


def update_cookies():
    with open(cookieFile, 'w') as output_file:
        output_file.write('session_cookie = ')


def update_leaderboard():
    r = requests.get(url)
    #with open(leaderboardFile, 'wb') as output_file:
    #    output_file.write(r.content)
    return True


if not exists(cookieFile):
    update_cookies()

# if something goes wring update cookies and retry
if not update_leaderboard():
    update_cookies()
    update_leaderboard()
