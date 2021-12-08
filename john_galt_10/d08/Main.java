package john_galt_10.d08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("john_galt_10/d08/input.txt");
        Scanner myReader = new Scanner(myObj);
        //parteUno(myReader);
        parteDue(myReader);
        myReader.close();
    }

    private static void parteUno(Scanner reader) {
        int ris = 0;
        while(reader.hasNextLine()) {
            String output = reader.nextLine().replace("|", "L").split(" L ")[1];
            for (String s : output.split(" ")) {
                if (s.length() == 2 | s.length() == 4 | s.length() == 3 | s.length() == 7) ris++;
            }
        }
        System.out.println("Soluzione parte uno: " + ris);
    }

    private static void parteDue(Scanner reader) {
        int soluzione = 0;
        while (reader.hasNextLine()){
            String riga = reader.nextLine().replace("|", "K");
            String input = riga.split(" K ")[0];
            String output = riga.split(" K ")[1];

            Entry[] entryOriginali = new Entry[10];
            Entry[] entryModificate = new Entry[10];

            int[] indiciSeri = new int[10];
            int i = 0;
            for (String s : input.split(" ")) {
                entryOriginali[i] = new Entry();
                entryOriginali[i].aggiungiEntry(s);
                entryOriginali[i].ordineAlf();
                entryModificate[i] = new Entry();
                entryModificate[i].aggiungiEntry(s);
                entryModificate[i].ordineAlf();
                i++;
            }

            int s = 0;
            for (Entry e : entryOriginali) {
                if (e.getCorrispondeA() != -1) indiciSeri[e.getCorrispondeA()] = s;
                s++;
            }


            System.out.println("indiciseri: ");
            for (int u : indiciSeri) {
                System.out.print(u + "   ");
            }
            System.out.println("");


            //identifica la codifica trattino in alto
            Entry uno = new Entry();
            Entry sette = new Entry();
            for (Entry e : entryOriginali) {
                if (e.getCorrispondeA() == 7) {
                    sette = e;
                }
                if (e.getCorrispondeA() == 1) {
                    uno = e;
                }
            }
            char alto = sette.diff(uno);
            System.out.println("alto: " + alto);

            //toglie da tutte le modifiche il carattere in alto
            for (Entry e : entryModificate) {
                e.remove(alto);
            }

            //identifica il 6
            //sono rimaste tre codifiche lunghe 5 - quella che non comprende i caratteri di '1' è 6
            int z = 0;
            for (Entry e : entryModificate) {
                if (e.getLength() == 5) {
                    if (!e.comprende(uno)) {
                        e.setCorrispondeA(6);
                        indiciSeri[6] = z;
                    }
                }
                z++;
            }
            System.out.println("indiciseri: ");
            for (int u : indiciSeri) {
                System.out.print(u + "   ");
            }
            System.out.println("");

            //identifica il 9
            //delle due rimaste lunghe 5 (escludendo il 6) una sola comprende '4' - quella sarà 9
            Entry quattro = new Entry();
            for (Entry e : entryModificate) {
                if (e.getCorrispondeA() == 4) {
                    quattro = e;
                }
            }
        /*System.out.print("quattro: ");
        quattro.print();
        System.out.print("\n");

        for (Entry e : entryModificate) {
            e.print();
        }
        System.out.print("\n");*/
            int q = 0;
            for (Entry e : entryModificate) {
                if (e.getLength() == 5) {
                    if (e.comprende(quattro) && e.getCorrispondeA() == -1) {
                        e.setCorrispondeA(9);
                        indiciSeri[9] = q;
                    }
                }
                q++;
            }
            System.out.println("indiciseri: ");
            for (int u : indiciSeri) {
                System.out.print(u + "   ");
            }
            System.out.println("");

            //l'ultima rimasta senza corrispondenza e lunga 5 è 0
            int x = 0;
            for (Entry e : entryModificate) {
                if (e.getLength() == 5) {
                    if (e.getCorrispondeA() == -1) {
                        e.setCorrispondeA(0);
                        indiciSeri[0] = x;
                    }
                }
                x++;
            }


            //ne mancano tre lunghe 4
            //dal confronto tra 8 e 9 scopro il trattino in basso a sx
            Entry otto = new Entry();
            Entry nove = new Entry();
            for (Entry e : entryModificate) {
                if (e.getCorrispondeA() == 8) {
                    otto = e;
                }
                if (e.getCorrispondeA() == 9) {
                    nove = e;
                }
            }
            System.out.println("otto");
            otto.print();
            System.out.println("nove");
            nove.print();
            char bassoSx = otto.diff(nove);
            System.out.println(bassoSx + " è bassosx");

            int f = 0;
            for (Entry e : entryModificate) {
                if (e.getCorrispondeA() == -1) {
                    if (e.contiene(bassoSx)) {
                        e.setCorrispondeA(2);
                        indiciSeri[2] = f;
                    }
                }
                f++;
            }


            //ne mancano due (3 e 5)
            //l'unico che comprende '1' è 3. L'altro è 5.
            int r = 0;
            for (Entry e : entryModificate) {
                if (e.getCorrispondeA() == -1) {
                    if (e.comprende(uno)) {
                        e.setCorrispondeA(3);
                        indiciSeri[3] = r;
                    } else {
                        e.setCorrispondeA(5);
                        indiciSeri[5] = r;
                    }
                }
                r++;
            }


            for (Entry e : entryModificate) {
                e.print();
            }

            System.out.println("indiciseri: ");
            for (int u : indiciSeri) {
                System.out.print(u + "   ");
            }


            //indiciseri associa i valori (indici array) alla progressiva entry
            List<Character> out1 = new ArrayList<>();
            List<Character> out2 = new ArrayList<>();
            List<Character> out3 = new ArrayList<>();
            List<Character> out4 = new ArrayList<>();


            for (char h : output.split(" ")[0].toCharArray()) out1.add(h);
            Collections.sort(out1, (c1, c2) -> {
                if (c1 > c2) return 1;
                else if (c1 < c2) return -1;
                else return 0;
            });

            for (char h : output.split(" ")[1].toCharArray()) out2.add(h);
            Collections.sort(out2, (c1, c2) -> {
                if (c1 > c2) return 1;
                else if (c1 < c2) return -1;
                else return 0;
            });

            for (char h : output.split(" ")[2].toCharArray()) out3.add(h);
            Collections.sort(out3, (c1, c2) -> {
                if (c1 > c2) return 1;
                else if (c1 < c2) return -1;
                else return 0;
            });

            for (char h : output.split(" ")[3].toCharArray()) out4.add(h);
            Collections.sort(out4, (c1, c2) -> {
                if (c1 > c2) return 1;
                else if (c1 < c2) return -1;
                else return 0;
            });

            int cifraUno = -1;
            for (int k = 0; k < 10; k++) {
                if (entryOriginali[k].uguali(out1)) cifraUno = entryModificate[k].getCorrispondeA();
            }
            int cifraDue = -1;
            for (int k = 0; k < 10; k++) {
                if (entryOriginali[k].uguali(out2)) cifraDue = entryModificate[k].getCorrispondeA();
            }
            int cifraTre = -1;
            for (int k = 0; k < 10; k++) {
                if (entryOriginali[k].uguali(out3)) cifraTre = entryModificate[k].getCorrispondeA();
            }
            int cifraQuattro = -1;
            for (int k = 0; k < 10; k++) {
                if (entryOriginali[k].uguali(out4)) cifraQuattro = entryModificate[k].getCorrispondeA();
            }

            soluzione += cifraUno * 1000 + cifraDue * 100 + cifraTre * 10 + cifraQuattro;
        }

        System.out.println("SOLUZIONE: " + soluzione);
    }
}
