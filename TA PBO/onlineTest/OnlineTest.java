import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class OnlineTest extends JFrame implements ActionListener {
    JLabel nameLabel, l, timerLabel;
    JTextField nameField;
    JRadioButton jb[] = new JRadioButton[5];
    JButton startButton, b1, b2, b3;
    ButtonGroup bg;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];
    Timer timer;
    int seconds;
    int timeRemaining = 300;
    String playerName;

    OnlineTest(String s) {
        super(s);

        nameLabel = new JLabel("Nama: ");
        nameLabel.setBounds(30, 10, 60, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(80, 10, 150, 20);
        add(nameField);

        startButton = new JButton("Mulai Ujian");
        styleButton(startButton);
        startButton.setBounds(250, 10, 120, 20);
        startButton.addActionListener(this);
        add(startButton);

        l = new JLabel();
        add(l);

        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            jb[i] = new JRadioButton();
            jb[i].setFont(new Font("Arial", Font.PLAIN, 14));
            add(jb[i]);
            bg.add(jb[i]);
        }

        b1 = new JButton("Lanjut");
        b2 = new JButton("Bookmark");
        b3 = new JButton("Kembali");

        styleButton(b1);
        styleButton(b2);
        styleButton(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        add(b1);
        add(b2);
        add(b3);

        timerLabel = new JLabel();
        timerLabel.setBounds(400, 40, 150, 20);
        add(timerLabel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                setTitle("Online Test - Time: " + seconds + " seconds");
            }
        });

        setLayout(null);
        setLocation(250, 100);
        getContentPane().setBackground(new Color(220, 240, 255));
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void styleButton(JButton button) {
        button.setBackground (Color.LIGHT_GRAY);
        button.setForeground (Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            playerName = nameField.getText();
            startButton.setEnabled(false);
            nameField.setEditable(false);
            set();
            timer.start();
        } else if (e.getSource() == b1) {
            if (check())
                count = count + 1;
            current++;
            set();
            if (current == 14) {
                b1.setEnabled(false);
                styleButton(b2);
                b2.setText("Hasil");
            }
        } else if (e.getActionCommand().equals("Bookmark")) {
            JButton bk = new JButton("Bookmark" + x);
            styleButton(bk);
            bk.setBounds(480, 20 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();
            if (current == 14)
                b2.setText("Result");
            setVisible(false);
            setVisible(true);
        } else if (e.getActionCommand().equals("Result")) {
            if (check())
                count = count + 1;
            timer.stop();
            showResult();
        } else if (e.getSource() == timer) {
            timeRemaining--;
            if (timeRemaining <= 0) {
                timer.stop();
                showResult();
            }
            updateTimerLabel();
        } else if (e.getSource() == b3) {
            if (current > 0) {
                current--;
                set();
                if (current == 0) {
                    b3.setEnabled(false);
                }
            }
        } else {
            for (int i = 0, y = 1; i < x; i++, y++) {
                if (e.getActionCommand().equals("Bookmark" + y)) {
                    if (check())
                        count = count + 1;
                    now = current;
                    current = m[y];
                    set();
                    ((JButton) e.getSource()).setEnabled(false);
                    current = now;
                }
            }
        }
    }

    void set() {
        if (current == 0) {
			l.setText("Pertanyaan ke 1: Apa makanan tradisional Indonesia yang terkenal?");
			
			jb[0].setText("Kimchi");
			jb[1].setText("Sushi");
			jb[2].setText("Rendang");
			jb[3].setText("Pad Thai");
        }
        if (current == 1) {
			l.setText("Pertanyaan ke 2: Berapa jumlah provinsi di Indonesia saat ini?");

			jb[0].setText("35");
			jb[1].setText("36");
			jb[2].setText("37");
			jb[3].setText("38");
        }
        if (current == 2) {
            l.setText("Pertanyaan ke 3: Kota manakah yang dikenal sebagai Kota Pelajar di Indonesia?");

			jb[0].setText("Malang");
			jb[1].setText("Bandung");
			jb[2].setText("Yogyakarta");
			jb[3].setText("Surabaya");
        }
        if (current == 3) {
            l.setText("Pertanyaan ke 4: Apa nama pulau terbesar kedua di Indonesia?");

			jb[0].setText("Jawa");
			jb[1].setText("Sumatera");
			jb[2].setText("Kalimantan");
			jb[3].setText("Papua");
        }
        if (current == 4) {
			l.setText("Pertanyaan ke 5: Siapakah arsitek utama di balik pembangunan Monas (Monumen Nasional) di Jakarta?");

			jb[0].setText("B.j habiebie");
			jb[1].setText("Soekarno");
			jb[2].setText("Soeharto");
			jb[3].setText("Ahmad Syuki");
        }
        if (current == 5) {
			l.setText("Pertanyaan ke 6: Apa nama gunung berapi yang terletak di perbatasan Jawa Timur dan Bali?");

			jb[0].setText("Bromo");
			jb[1].setText("Merapi");
			jb[2].setText("Agung");
			jb[3].setText("Ijen");
        }
        if (current == 6) {
            l.setText("Pertanyaan ke 7: Siapakah penerima Nobel Sastra Indonesia pertama?");

			jb[0].setText("Chairil Anwar");
			jb[1].setText("Pramoedya Ananta Toer");
			jb[2].setText("Goenawan Mohamad");
			jb[3].setText("Taufik Ismail");
        }
        if (current == 7) {
			l.setText("Pertanyaan ke 8: Kota manakah yang dikenal sebagai \"Kota Seribu Gudang\"?");
			
			jb[0].setText("Medan");
			jb[1].setText("Jakarta");
			jb[2].setText("Surabaya");
			jb[3].setText("Makassar");
        }
        if (current == 8) {
			l.setText("Pertanyaan ke 9: Apa festival tradisional yang dirayakan di Yogyakarta?");
			
			jb[0].setText("Festival Kuda Lumping");
			jb[1].setText("Festival Jember Fashion Carnival");
			jb[2].setText("Festival Wayang Kulit");
			jb[3].setText("Festival Gamelan");
        }
        if (current == 9) {
            l.setText("Pertanyaan ke 10: Apa nama danau terbesar di Indonesia?");
			
			jb[0].setText("Danau Toba");
			jb[1].setText("Danau Singkarak");
			jb[2].setText("Danau Ranau");
			jb[3].setText(" Danau Sentani");

		}
		if(current==10) {
			l.setText("Pertanyaan ke 11: Kota manakah yang terkenal dengan industri batiknya?");

			jb[0].setText("Solo");
			jb[1].setText("Malang");
			jb[2].setText("Yogyakarta");
			jb[3].setText("Bandung");
			
		}
		if(current==11) {
			l.setText("Pertanyaan ke 12: Apa nama hewan nasional Indonesia?");

			jb[0].setText("Harimau Sumatera");
			jb[1].setText("Orangutan");
			jb[2].setText("Komodo");
			jb[3].setText("Gajah Sumatera");
		}
		if(current==12) {
			l.setText("Pertanyaan ke 13: Apa nama candi Buddha terbesar di Indonesia?");

			jb[0].setText("Borobudur");
			jb[1].setText("Prambanan");
			jb[2].setText("Mendut");
			jb[3].setText("Plaosan");
		}
		if(current==13) {
			l.setText("Pertanyaan ke 14: Apa nama tari tradisional dari Bali?");

			jb[0].setText("Saman");
			jb[1].setText("Pendet");
			jb[2].setText("Reog");
			jb[3].setText("Zapin");
		}
		if(current==14) {
			l.setText("Pertanyaan ke 15: Kapan Hari Kemerdekaan Indonesia dirayakan?");

			jb[0].setText("7 Agustus");
			jb[1].setText("17 Agustus");
			jb[2].setText("30 Juli");
			jb[3].setText("1 Juni");
            timer.stop();
            int timeInSeconds = seconds;
            updateTimerLabel();
            saveResultToFile(playerName, count, timeInSeconds);
        }

        l.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 90; i += 30, j++)
            jb[j].setBounds(50, 80 + i, 200, 20);

        b1.setBounds(100, 240, 100, 30);
        b2.setBounds(220, 240, 120, 30);
        b3.setBounds(360, 240, 100, 30);

        if (timerLabel == null) {
            timerLabel = new JLabel();
            timerLabel.setBounds(400, 40, 150, 20);
            add(timerLabel);
        }

        if (timer == null) {
            timer = new Timer(1000, this);
            timer.start();
        }
    }

    boolean check() {
        if (current == 0)
            return jb[0].isSelected();

        else if (current == 1)
            return jb[2].isSelected();

        else if (current == 2)
            return jb[1].isSelected();

        else if (current == 3)
            return jb[0].isSelected();

        else if (current == 4)
            return jb[0].isSelected();

        else if (current == 5)
            return jb[1].isSelected();

        else if (current == 6)
            return jb[3].isSelected();

        else if (current == 7)
            return jb[0].isSelected();

        else if (current == 8)
            return jb[3].isSelected();

        else if (current == 9)
            return jb[1].isSelected();

        return false;
    }

    void showResult() {
        int finalScore = count;
        String resultMessage = "Hasil akhir " + playerName + ": " + finalScore + "/10";
        resultMessage += "\nWaktu Pengerjaan: " + seconds + " detik";
        JOptionPane.showMessageDialog(this, resultMessage, "Hasil", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    void updateTimerLabel() {
        if (timerLabel != null) {
            timerLabel.setText("\n\n" + "Waktu Tersisa: " + timeRemaining + " detik");
        }
    }

    void saveResultToFile(String playerName, int finalScore, int timeInSeconds) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("hasil_ujian.txt", true))) {
            writer.write("Hasil akhir " + playerName + ": " + finalScore + "/10\n");
            writer.write("Waktu Pengerjaan: " + timeInSeconds + " detik\n");
            System.out.println("Hasil disimpan ke hasil_ujian.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String s[]) {
        SwingUtilities.invokeLater(() -> new OnlineTest("Ujian Online Java"));
    }
}