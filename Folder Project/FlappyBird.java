import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    // image attributes
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // player
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;
    private boolean isGameOver = false;
    private int score = 0;

    // UI untuk menampilkan skor
    private JLabel scoreLabel;

    // pipes attributes
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    // game logic
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;

    // constructor
    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        setLayout(null);
        addKeyListener(this);
        // setBackground(Color.blue);

        // load images
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        // Inisialisasi player & pipa
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        // Label untuk menampilkan skor
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(20, 20, 200, 30);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(scoreLabel);

        // Timer untuk membuat pipa
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pipa");
                placePipes();
            }
        });
        pipesCooldown.start();

        // Timer untuk game loop (frame rate 60 FPS)
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    // Method untuk menempatkan pipa atas & bawah dengan jarak yang di-random
    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage, true);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage, false);
        pipes.add(lowerPipe);
    }

    // Method menggambar seluruh elemen game
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    // Method untuk memindahkan posisi player dan pipa
    public void move() {
        if (!isGameOver) {
            // Player jatuh ke bawah karena gravitasi
            player.setVelocityY(player.getVelocityY() + gravity);
            player.setPosY(player.getPosY() + player.getVelocityY());
            player.setPosY(Math.max(player.getPosY(), 0)); // Supaya tidak keluar layar atas

            // Geser semua pipa ke kiri
            for (Pipe pipe : pipes) {
                pipe.setPosX(pipe.getPosX() + pipe.getVelocityY());
            }
        }
    }

    // Method ini dipanggil tiap frame (60x per detik)
    @Override
    public void actionPerformed(ActionEvent e) {
        move();    // update posisi objek
        repaint(); // gambar ulang tampilan
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Event ketika tombol ditekan
    @Override
    public void keyPressed(KeyEvent e) {
        // Tekan spasi untuk terbang
        if (!isGameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        }

        // Tekan R untuk restart saat game over
        if (isGameOver && e.getKeyCode() == KeyEvent.VK_R) {
            restartGame();
        }
    }

    // Method untuk mereset ulang permainan
    public void restartGame() {
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        pipes.clear();
        score = 0;
        isGameOver = false;
        scoreLabel.setText("Score: 0");
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    // Method utama untuk menggambar ulang komponen game
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Gambar background & player
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

        // Gambar pipa dan deteksi tabrakan
        for (Pipe pipe : pipes) {
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());

            // Deteksi tabrakan player dengan pipa
            if (!isGameOver && playerRect.intersects(pipeRect)) {
                isGameOver = true;
            }

            // Cek apakah player berhasil melewati pipa atas → tambah skor
            if (pipe.isUpper() && !pipe.isPassed() && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.setPassed(true);
                score++;
                scoreLabel.setText("Score: " + score);
            }
        }

        // Cek apakah player jatuh keluar layar bawah → game over
        if (player.getPosY() > getHeight()) {
            isGameOver = true;
        }

        // Jika game over, tampilkan pesan
        if (isGameOver) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("GAME OVER", 90, 250);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press R to Restart", 100, 280);
        }
    }
}
