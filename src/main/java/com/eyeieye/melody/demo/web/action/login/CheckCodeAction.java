package com.eyeieye.melody.demo.web.action.login;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eyeieye.melody.web.url.URLBroker;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.gimpy.BlockGimpyRenderer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.gimpy.RippleGimpyRenderer;
import nl.captcha.noise.CurvedLineNoiseProducer;
import nl.captcha.text.producer.TextProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.util.captcha.EasyCharTextProducer;
import com.eyeieye.melody.util.captcha.FixedWordRenderer;
import com.eyeieye.melody.web.util.ResponseUtil;

/**
 *
 * @author fish
 *
 */
@Controller
@RequestMapping("/checkcode")
public class CheckCodeAction {

	@Autowired
	private URLBroker mailServer;


	private static final List<Font> englishFonts = Arrays.asList(new Font(
			"Lucida Sans", Font.ITALIC, 55), new Font("SansSerif", Font.ITALIC,
			60));

	/**
	 * 检查验证码，ajax检查
	 *
	 * @param user
	 * @param token
	 * @return
	 */
	@RequestMapping("/check.json")
	@ResponseBody
	public boolean check(User user, String token) {
		String sessionToken = user.getLastToken();
		if (StringUtil.isEmpty(sessionToken)) {
			return false;
		}
		if (sessionToken.equals(token)) {
			return true;
		}
		return false;
	}

	@RequestMapping("/simple.htm")
	public void simple(HttpServletRequest request,
			HttpServletResponse response, User user,
			HttpSession httpSession) throws IOException {
		ResponseUtil.preventCaching(request, response);
		Captcha captcha = new Captcha.Builder(220, 80)
				.addText(new EasyCharTextProducer(),
						new FixedWordRenderer(Color.black, englishFonts))
				.gimp(new RippleGimpyRenderer())
				.gimp(new BlockGimpyRenderer())
				.gimp(new DropShadowGimpyRenderer())
				.addBackground(new GradiatedBackgroundProducer())
				.addNoise(new CurvedLineNoiseProducer(Color.black, 1.8f))
				.addNoise(new CurvedLineNoiseProducer(Color.black, 1.3f))
				.build();
		response.setContentType("image/jpeg");
		user.setLastToken(captcha.getAnswer());
		httpSession.setAttribute(user.NAME, user);
		OutputStream os = response.getOutputStream();
		ImageIO.write(captcha.getImage(), "JPEG", os);
	}

	@RequestMapping("/arithmetic.htm")
	public void arithmetic(HttpServletRequest request,
			HttpServletResponse response, User user,
			HttpSession httpSession) throws IOException {
		ResponseUtil.preventCaching(request, response);
		final Random random = new Random();
		final int one = 1 + random.nextInt(49);
		final int two = random.nextInt(49);

		Captcha captcha = new Captcha.Builder(220, 80)
				.addText(new TextProducer() {
					public String getText() {
						if (random.nextBoolean()) {
							return new StringBuilder().append(one).append('+')
									.append(two).toString();
						} else {
							if (one > two) {
								return new StringBuilder().append(one)
										.append('-').append(two).toString();
							} else {
								return new StringBuilder().append(two)
										.append('-').append(one).toString();
							}
						}
					}
				}, new FixedWordRenderer(Color.black, englishFonts))
				.gimp(new RippleGimpyRenderer())
				.gimp(new DropShadowGimpyRenderer())
				.addNoise(new CurvedLineNoiseProducer(Color.black, 2.8f))
				.addNoise(new CurvedLineNoiseProducer(Color.black, 1.3f))
				.addBackground(new GradiatedBackgroundProducer()).build();
		response.setContentType("image/jpeg");
		user.setLastToken(captcha.getAnswer());
		httpSession.setAttribute(user.NAME, user);
		OutputStream os = response.getOutputStream();
		ImageIO.write(captcha.getImage(), "JPEG", os);
	}

}
