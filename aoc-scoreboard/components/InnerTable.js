import React, { useEffect } from "react";
import { useState } from "react";

import ProgrammingLanguage from "./ProgrammingLanguage";

export default function InnerTable(props) {
  const days = [
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
    22, 23, 24, 25,
  ];

  const [GitHubRepo, setGitHubRepo] = useState([]);
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);

  useEffect(() => {
    fetch(
      "https://api.github.com/repos/tommimon/advent-of-code-2021/git/trees/master?recursive=1"
    )
      .then((res) => res.json())
      .then(
        (result) => {
          setGitHubRepo(result);
          setIsLoaded(true);
        },
        (error) => {
          setGitHubRepo(true);
          setError(error);
        }
      );
  }, []);

  const printStar = (num) => {
    if (num == 2) {
      return (
        <p class="flex justify-center align-middle">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-4 w-4"
            viewBox="0 0 20 20"
            fill="currentColor"
          >
            <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
          </svg>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-4 w-4"
            viewBox="0 0 20 20"
            fill="currentColor"
          >
            <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
          </svg>
        </p>
      );
    } else {
      return (
        <p class="flex justify-center align-middle">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-4 w-4"
            viewBox="0 0 20 20"
            fill="currentColor"
          >
            <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
          </svg>
        </p>
      );
    }
  };

  return (
    <div>
      {days.map((day) => (
        <tr
          class={
            "flex w-full" +
            (day % 2 === 1
              ? "border-b border-gray-200 bg-white bg-opacity-90"
              : "border-b border-gray-200 bg-white bg-opacity-100")
          }
        >
          <td class="w-20 lg:w-full py-4 px-2 text-gray-800 text-sm font-medium text-center m-auto">
            <a
              href={"https://adventofcode.com/2021/day/" + day}
              target="_blank"
            >
              {day}
            </a>
          </td>
          {props.Scoreboard.members
            ? Object.entries(props.Scoreboard.members).map(([key, value]) => (
                <td class="w-20 lg:w-full py-4 px-2 text-center m-auto">
                  {value.completion_day_level[day] ? (
                    value.completion_day_level[day][1] ? (
                      value.completion_day_level[day][2] ? (
                        props.NameToFolder[value.name] ? (
                          <p>
                            <a
                              href={
                                "https://github.com/Tommimon/advent-of-code-2021/tree/master/" +
                                props.NameToFolder[value.name] +
                                "/d" +
                                (day < 10 ? "0" : "") +
                                day
                              }
                              target="_blank"
                              class="text-yellow-400"
                            >
                              {printStar(2)}
                            </a>
                            <ProgrammingLanguage
                              name={value.name}
                              day={day}
                              NameToFolder={props.NameToFolder}
                              GitHubRepo={GitHubRepo}
                            />
                          </p>
                        ) : (
                          printStar(2)
                        )
                      ) : props.NameToFolder[value.name] ? (
                        <p>
                          <a
                            href={
                              "https://github.com/Tommimon/advent-of-code-2021/tree/master/" +
                              props.NameToFolder[value.name] +
                              "/d" +
                              (day < 10 ? "0" : "") +
                              day
                            }
                            target="_blank"
                            class="text-yellow-400"
                          >
                            {printStar(1)}
                          </a>
                          <ProgrammingLanguage
                            name={value.name}
                            day={day}
                            NameToFolder={props.NameToFolder}
                            GitHubRepo={GitHubRepo}
                          />
                        </p>
                      ) : (
                        printStar(1)
                      )
                    ) : (
                      "-"
                    )
                  ) : (
                    "-"
                  )}
                </td>
              ))
            : ""}
        </tr>
      ))}
    </div>
  );
}
