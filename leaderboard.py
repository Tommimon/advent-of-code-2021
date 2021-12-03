from os.path import exists
import time
try:
    import requests
except ModuleNotFoundError:
    print("Can't find requests module, stopping update")
    exit(0)

URL = 'https://adventofcode.com/2021/leaderboard/private/view/976110.json'
COOKIE_FILE = 'cookies'
LEADERBOARD_FILE = 'leaderboard.json'
DELTA_TIME = 900


def update_cookies():
    cookies = input("Enter new cookies: ")
    with open(COOKIE_FILE, 'w') as output_file:
        output_file.write(cookies)


def update_leaderboard():
    ts = round(time.time())
    with open(LEADERBOARD_FILE, "r") as input_file:
        data = input_file.readlines()[0]
    old_ts = int(data.split(',"event"')[0].split('"last_modified":')[1])

    if ts >= old_ts + DELTA_TIME:
        print("Current leaderboard is too old, updating...")
        try:
            with open(COOKIE_FILE, "r") as input_file:
                cookies = input_file.readlines()[0]
            headers = {"Cookie": cookies}
        except IndexError:
            print("Cookies not found")
            return False
        print("Cookies loaded successfully")

        txt = requests.get(URL, headers=headers).text
        if txt.find('"owner_id":"976110"') == -1:  # check leaderboard
            print("An error occurred while downloading the new leaderboard")
            return False
        print("New leaderboard downloaded successfully")
        txt = txt.replace('"event"', '"last_modified":' + str(ts) + ',"event"')
        with open(LEADERBOARD_FILE, 'w') as output_file:
            output_file.write(txt)
    else:
        print("Not enough time elapsed since last update, skipping leaderboard update")
    return True


if not exists(COOKIE_FILE):
    print("Cookies not found")
    update_cookies()

# if something goes wring update cookies and retry
if not update_leaderboard():
    update_cookies()
    if update_leaderboard():
        print("Leaderboard is up to date")
    else:
        print("Couldn't update the leaderboard")
else:
    print("Leaderboard is up to date")
