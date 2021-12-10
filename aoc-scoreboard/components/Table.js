import React, { useEffect } from "react";
import { useState } from "react";
import InnerTable from "./InnerTable";
import HeaderTable from "./HeaderTable";

export default function Table(props) {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [Scoreboard, setScoreboard] = useState([]);

  const NameToFolder = {
    "Alessandro Nazzari": false,
    "Marco Molè": "marcomole00",
    "Matteo Negro": "MatteoBlack",
    "riccardo-negri": "riccardo_negri",
    Gonduls: "Gonduls",
    Tommimon: "tommimon",
    "Davide Palmiotti": "mynam3isg00d",
    marcoparadina: "marcoparadina",
    gingervi: false,
    alexeats: false,
    "john-galt-10": "john_galt_10",
  };

  useEffect(() => {
    fetch(
      "https://raw.githubusercontent.com/Tommimon/advent-of-code-2021/master/leaderboard.json"
    )
      .then((res) => res.json())
      .then(
        (result) => {
          // replace members with a sorted array
          result.members = Object.entries(result.members).sort((a, b) => a[1].local_score < b[1].local_score ? 1 : -1);
          setScoreboard(result);
          setIsLoaded(true);
        },
        (error) => {
          setScoreboard(true);
          setError(error);
        }
      );
  }, []);

  return (
    <div>
      <table class="text-left w-full block overflow-x-auto">
        <thead class="hidden lg:flex w-full">
          <tr class="flex items-center w-full bg-white text-gray-800 capitalize text-sm leading-normal">
            <th class="w-28 lg:w-full py-3 text-center ">Day</th>
            {Scoreboard.members ? (
              <HeaderTable
                Scoreboard={Scoreboard}
                NameToFolder={NameToFolder}
              />
            ) : (
              ""
            )}
          </tr>
        </thead>
        <tbody
          class=" table lg:block flex-col justify-between overflow-y-scroll w-full text-gray-600 text-sm "
          style={{ height: 65 + "vh" }}
        >
          <tr class="lg:hidden flex items-center w-full bg-white text-gray-800 capitalize text-sm leading-normal">
            <th class="w-28 lg:w-full py-3 text-center ">Day</th>
            {Scoreboard.members ? (
              <HeaderTable
                Scoreboard={Scoreboard}
                NameToFolder={NameToFolder}
              />
            ) : (
              ""
            )}
          </tr>
          <InnerTable Scoreboard={Scoreboard} NameToFolder={NameToFolder} />
        </tbody>
      </table>
    </div>
  );
}
