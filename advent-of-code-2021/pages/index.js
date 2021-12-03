import Head from "next/head";
import Table from "../components/Table";

export default function Home() {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen py-2">
      <Head>
        <title>Advent of Code 2021</title>
        <link rel="icon" href="https://adventofcode.com/favicon.png" />
      </Head>

      <main className="flex flex-col items-center justify-center w-full flex-1 px-20 text-center">
        <div class="container">
          <h1 class="text-3xl uppercase mb-8 leading-normal font-semibold">Scoreboard Advent of Code 2021</h1>
          <Table />
        </div>
      </main>

      <footer className="flex items-center justify-center w-full h-24 border-t">
        <a
          className="flex items-center justify-center"
          href="https://github.com/Tommimon/advent-of-code-2021"
          target="_blank"
          rel="noopener noreferrer"
        >
          Powered by{" "}
          <img src="Poli.jpg" alt="Poli" className="h-14 ml-2" />
        </a>
      </footer>
    </div>
  );
}
