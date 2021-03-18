package Try_Google_Speech_Recognition_Simple;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;
import model.LaTex;
import model.PhuongtrinhBac1;
import model.PhuongtrinhBac2;
import net.sourceforge.javaflacencoder.FLACFileWriter;

public class TryGoogleSpeechRecognitionSimple implements GSpeechResponseListener {

	// giai phuong trinh bac 1
	public static String giaiPTBac1(String ouput) {
		String output = ouput;
		StringTokenizer token = new StringTokenizer(output, " ");
		List<String> list = new ArrayList<String>();
		List<Integer> listNum = new ArrayList<Integer>();

		while (token.hasMoreTokens())
			list.add(token.nextToken());

		for (int i = 0; i < list.size(); i++) {
			if (isNumber(list.get(i)) && (i != 0) && (list.get(i - 1).equals("-") || list.get(i - 1).equals("trừ"))) {
				listNum.add(-Integer.parseInt(list.get(i)));
			} else if (isNumber(list.get(i))) {
				listNum.add(Integer.parseInt(list.get(i)));
			}
		}

		PhuongtrinhBac1 pt = new PhuongtrinhBac1();

		if (listNum.size() == 3) {
			pt.setA(listNum.get(0));
			pt.setB(listNum.get(1));
			pt.setC(listNum.get(2));
		} else if (listNum.size() == 2) {
			pt.setA(listNum.get(0));
			pt.setB(0);
			pt.setC(listNum.get(1));
		} else
			return "Không thể giải";

		return pt.getResult();
	}

	// giai phuong trinh bac 2
	public static String giaiPTBac2(String output) {
		StringTokenizer token = new StringTokenizer(output, " ");
		List<String> list = new ArrayList<String>();
		List<Integer> listNum = new ArrayList<Integer>();

		PhuongtrinhBac2 pt = new PhuongtrinhBac2();

		int countX = 0;

		while (token.hasMoreTokens())
			list.add(token.nextToken());

		for (int i = 0; i < list.size(); i++) {
			if (isNumber(list.get(i)) && (i != 0) && (list.get(i - 1).equals("-") || list.get(i - 1).equals("trừ"))) {
				listNum.add(-Integer.parseInt(list.get(i)));
			} else if (isNumber(list.get(i))) {
				listNum.add(Integer.parseInt(list.get(i)));
			}
			System.out.println(list.get(i) + " ");
			if (list.get(i).equals("x"))
				countX++;
		}

		if (listNum.size() == 4) {
			if (listNum.get(0) == 0) {
				pt.setA(0);
				pt.setB(listNum.get(1));
				pt.setC(listNum.get(2));
				pt.setD(listNum.get(3));
				return pt.getResult();
			} else {
				pt.setA(listNum.get(0));
				pt.setB(listNum.get(1));
				pt.setC(listNum.get(2));
				pt.setD(listNum.get(3));
				return pt.getResult();
			}

		} else if (listNum.size() == 3) {
			if (listNum.get(0) == 0 && countX == 2) {
				pt.setA(0);
				pt.setB(listNum.get(1));
				pt.setC(0);
				pt.setD(listNum.get(2));
				return pt.getResult();
			} else if (listNum.get(0) != 0 && countX == 2) {
				pt.setA(listNum.get(0));
				pt.setB(listNum.get(1));
				pt.setC(0);
				pt.setD(listNum.get(2));
				return pt.getResult();
			} else if (listNum.get(0) != 0 && countX == 1) {
				pt.setA(listNum.get(0));
				pt.setB(0);
				pt.setC(listNum.get(1));
				pt.setD(listNum.get(2));
				return pt.getResult();
			}

		} else if (listNum.size() == 2) {
			pt.setA(listNum.get(0));
			pt.setB(0);
			pt.setC(0);
			pt.setD(listNum.get(1));
			return pt.getResult();
		}

		return "PT khong the giai duoc";

	}

	// kiem tra no co phai la so khong?
	public static boolean isNumber(String s) {
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		final Microphone mic = new Microphone(FLACFileWriter.FLAC);
		GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

		duplex.setLanguage("vi");

		JFrame frame = new JFrame("Siêu Trí Tuệ Việt Nam 4.0");
		frame.setDefaultCloseOperation(3);
		JTextArea response = new JTextArea();
		response.setEditable(false);
		response.setWrapStyleWord(true);
		response.setLineWrap(true);
		response.setFont(new Font("Serif", Font.PLAIN, 21));
		final JButton record = new JButton("Thu Âm");
		final JButton stop = new JButton("Dừng Lại");
		record.setBackground(Color.GREEN);
		record.setForeground(Color.BLACK);
		stop.setBackground(Color.RED);
		stop.setForeground(Color.WHITE);
		stop.setEnabled(false);

		record.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new Thread(() -> {
					try {
						duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}).start();
				record.setEnabled(false);
				stop.setEnabled(true);
			}
		});
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mic.close();
				duplex.stopSpeechRecognition();
				record.setEnabled(true);
				stop.setEnabled(false);
			}
		});

		frame.getContentPane().setLayout(new BorderLayout());
		JPanel layoutHead = new JPanel();
		layoutHead.setLayout(new BoxLayout(layoutHead, BoxLayout.Y_AXIS));
		JLabel infoJlabel = new JLabel("Giải Phương Trình", JLabel.CENTER);
		infoJlabel.setFont(new Font("Serif", Font.BOLD, 40));
//		infoJlabel.setForeground(Color.);

		JPanel layoutChild = new JPanel(new FlowLayout());
		JTextArea infoJLabel = new JTextArea(1, 20);
		layoutHead.add(infoJlabel);
		layoutChild.add(record);
		layoutChild.add(infoJLabel);
		layoutHead.add(layoutChild);
		frame.add(layoutHead, BorderLayout.NORTH);

		JScrollPane scroll = new JScrollPane(response);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		JPanel recordBar = new JPanel();
		frame.getContentPane().add(recordBar, BorderLayout.SOUTH);
		recordBar.add(stop);
		frame.setVisible(true);
		frame.pack();
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);

		duplex.addResponseListener(new GSpeechResponseListener() {
			String old_text = "";

			// Xu li chuoi sau khi da nhan dang
			public void onResponse(GoogleResponse gr) {
				String output = "";
				output = gr.getResponse();
				System.out.println(output);
				infoJLabel.setText("");
				response.setText("");
				infoJLabel.append(this.old_text);
				infoJLabel.append(output);

				if (output.contains("x") && (output.contains("=") || output.contains("bằng"))) {
					if (output.contains("bình") || output.contains("bình phương")) {
						response.append(giaiPTBac2(output));

					} else {
						response.append(giaiPTBac1(output));
					}
				} else {
					response.append("Cu phap khong dung vui long noi lai");

				}

			}

		});

	}

	@Override
	public void onResponse(GoogleResponse paramGoogleResponse) {
		// TODO Auto-generated method stub

	}
}

//pt.getResult();
//ArrayList<String> listBieu = pt.getArrBieuthuc();
//ArrayList<String> listChuoi = pt.getArrChuoi();
//for (int i = 0; i < listBieu.size(); i++) {
//	result.add(new JLabel(listChuoi.get(i)));
//	JLabel jl1 = new JLabel();
//	jl1.setIcon(latex.getIconLaTex(listBieu.get(i)));
//	result.add(jl1);
//}