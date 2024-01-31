import cv2

cap = cv2.VideoCapture("./dataset/target/hope_prac.mp4")

output_path = "output.mp4"
fps = 30

if cap.isOpened():
    ret, img = cap.read()

    img_h, img_w, img_c = img.shape

    out = cv2.VideoWriter(output_path, cv2.VideoWriter_fourcc(*'mp4v'), fps, (img_w, img_h))

    while ret:
        ret, img = cap.read()
        if not ret:
            break

        cap.grab()

        text = "holymoly"
        font = cv2.FONT_HERSHEY_SIMPLEX
        fontSize = 1
        color = (255, 255, 255)
        thickness = 2
        cv2.putText(img, text, (10, 50), font, fontSize, color, thickness, cv2.LINE_AA)

        out.write(img)

out.release()