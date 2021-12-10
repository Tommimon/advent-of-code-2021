import Head from "next/head";
import Table from "../components/Table";

export default function Home() {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen py-2">
      <Head>
        <title>Advent of Code 2021</title>
        <link rel="icon" href="https://adventofcode.com/favicon.png" />
        <link
          rel="stylesheet"
          href="https://cdn.jsdelivr.net/gh/devicons/devicon@v2.14.0/devicon.min.css"
        />
        <style>
          @import
          url('https://fonts.googleapis.com/css2?family=Source+Code+Pro&display=swap');
        </style>
      </Head>

      <main className="flex flex-col items-center justify-center w-full flex-1 px-8 lg:px-20 text-center">
        <div class="container">
          <h1 class="text-3xl py-5 leading-normal capitalize font-semibold text-white">
            Scoreboard{" "}
            <a href="https://adventofcode.com/" target="_blank">
              Advent of Code 2021
            </a>
          </h1>
          <Table />
        </div>
      </main>

      <footer className=" inline-flex items-center justify-center w-full py-5 text-white font-medium">
        <p>
          Powered by our Amazing Team
          <a
            className=" flex items-center justify-center text-yellow-400"
            href="https://github.com/Tommimon/advent-of-code-2021"
            target="_blank"
            rel="noopener noreferrer"
          >
            Discover us on Github!
          </a>
        </p>
      </footer>
    </div>
  );
}
